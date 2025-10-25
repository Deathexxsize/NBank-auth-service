package NBank.auth_service.service;

import NBank.auth_service.dto.authDTOs.LogoutRequest;
import NBank.auth_service.dto.authDTOs.RefreshRequest;
import NBank.auth_service.dto.authDTOs.RefreshResponse;
import NBank.auth_service.model.RefreshToken;
import NBank.auth_service.model.User;
import NBank.auth_service.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshService {
    private final RefreshRepository refreshRepo;
    private final JwtService jwtService;

    public RefreshResponse refresh(RefreshRequest request, UUID userId) {
        RefreshToken refreshToken = refreshRepo.getRefreshTokenByToken(request.refreshToken())
                .orElseThrow(() -> new RuntimeException("Token not found"));
        if (!refreshToken.getRevoked()) {
            throw new RuntimeException("Token id revoked");
        }
        if (refreshToken.getExpiresAt().isAfter(Instant.now())) {
            throw new RuntimeException("The validity period of the token has expired");
        }
        if (refreshToken.getUser().getId() != userId) {
            throw new RuntimeException("This is not a user token");
        }
        String newAccessToken = jwtService.generateAccessToken(userId, refreshToken.getUser().getEmail());
        RefreshResponse response = new RefreshResponse(newAccessToken);
        return response;
    }

    public void saveRefreshToken(String token, User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(token);
        refreshToken.setExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS));
        refreshRepo.save(refreshToken);
    }
}
