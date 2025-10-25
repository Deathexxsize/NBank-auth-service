package NBank.auth_service.service;

import NBank.auth_service.dto.authDTOs.LoginRequest;
import NBank.auth_service.dto.authDTOs.LoginResponse;
import NBank.auth_service.dto.authDTOs.LogoutRequest;
import NBank.auth_service.model.RefreshToken;
import NBank.auth_service.model.User;
import NBank.auth_service.repository.RefreshRepository;
import NBank.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final RefreshService refreshService;
    private final RefreshRepository refreshRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public LoginResponse login(LoginRequest request) {
        User user = userRepo.getUserByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtService.generateAccessToken(user.getId() ,user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());
        refreshService.saveRefreshToken(refreshToken, user);
        LoginResponse response = new LoginResponse(accessToken, refreshToken);
        return response;
    }

    public String logout(LogoutRequest request) {
        if (refreshRepo.deleteRefreshTokenByToken(request.refreshToken())){
            return "Success";
        }
        throw new RuntimeException("Token is not found or couldn't delete");
    }
}
