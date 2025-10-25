package NBank.auth_service.dto.passwordDTOs;

public record ForgotRequest (
        String email
        // Можно потом добавить сброс по номеру
) {
}
