package NBank.auth_service.dto.passwordDTOs;

public record VerifyCodeRequest(
        String email,
        Integer verificationCode
) {
}
