package NBank.auth_service.dto;

import java.util.Date;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        Long phoneNumber,
        String password,
        Date dateOfBirth
) {
}
