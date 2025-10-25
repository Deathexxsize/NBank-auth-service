package NBank.auth_service.dto.authDTOs;

public record LogoutRequest(
        String refreshToken
) {
}
