package NBank.auth_service.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public Boolean dataValidator(
            String email,
            Long phoneNumber,
            String password
        ) {
        if (email == null || !email.contains("@gmail.com")) {
            return false;
        }
        if (phoneNumber == null ) {
            return false;
        }
        if (password == null || password.length() < 8){
            return false;
        }
        return true;
    }
}
