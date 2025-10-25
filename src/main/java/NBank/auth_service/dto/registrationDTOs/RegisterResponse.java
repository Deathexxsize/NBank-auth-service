package NBank.auth_service.dto.registrationDTOs;

import java.util.UUID;

public record RegisterResponse(
        UUID id,
        String accessToken,
        String refreshToken,
        String message
) {
}
