package NBank.auth_service.dto.passwordDTOs;

public record ResetResponse(
        String accessToken,
        String refreshToken,
        String message
) {
}
