package NBank.auth_service.dto.authDTOs;

public record LoginRequest(
        String email,
        String password
) {
}
