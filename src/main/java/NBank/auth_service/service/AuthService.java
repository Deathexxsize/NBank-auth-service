package NBank.auth_service.service;

import NBank.auth_service.dto.LoginRequest;
import NBank.auth_service.exceptions.InvalidDataException;
import NBank.auth_service.model.User;
import NBank.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public String login(LoginRequest request) {
        User user = userRepo.getUserByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        getAccessToken(user.getId(), request.email(), request.password());
        return "Success";
    }

    public String refresh() {
        return "Success";
    }

    public String logout() {
        return "Success";
    }

    public String validate() {
        return "Success";
    }

    private String getAccessToken(UUID id, String email, String password) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, password
        ));

        if (authentication.isAuthenticated()) {
            return jwtService.generateAccessToken(id, email);
        } else {
            return "Fail :)";
        }
    }
}
