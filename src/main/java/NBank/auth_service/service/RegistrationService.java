package NBank.auth_service.service;

import NBank.auth_service.clients.UserClient;
import NBank.auth_service.dto.registrationDTOs.RegisterRequest;
import NBank.auth_service.dto.registrationDTOs.RegisterResponse;
import NBank.auth_service.model.User;
import NBank.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final ValidationService validationService;
    private final UserRepository userRepo;
    private final UserClient userClient;
    private final JwtService jwtService;
    private final RefreshService refreshService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public RegisterResponse userRegister(RegisterRequest request) {
        validationService.dataValidator(
                request.email(),
                request.phoneNumber(),
                request.password()
        );

        User authUser = new User();
        authUser.setEmail(request.email());
        authUser.setPhoneNumber(request.phoneNumber());
        authUser.setPassword(encoder.encode(request.password()));
        authUser.setCreatedAt(Instant.now());

        userRepo.save(authUser);

        try {
//             userClient.createUserProfile(request); // еще не реализовано
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании профиля в User Service", e);
        }
        String accessToken = jwtService.generateAccessToken(authUser.getId(), authUser.getEmail());
        String refreshToken = jwtService.generateRefreshToken(authUser.getEmail());
        refreshService.saveRefreshToken(refreshToken, authUser);
        return new RegisterResponse(
                authUser.getId(),
                accessToken,
                refreshToken,
                "Success");
    }
}
