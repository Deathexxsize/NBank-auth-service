package NBank.auth_service.dto.authDTOs;

public record LoginResponse (
   String accessToken,
   String RefreshToken
){ }
