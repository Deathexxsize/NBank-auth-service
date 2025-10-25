package NBank.auth_service.dto.passwordDTOs;

public record ResetRequest(
        String email,
        String newPassword,
        String repeatNewPassword
) {
}
