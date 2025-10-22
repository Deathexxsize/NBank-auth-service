package NBank.auth_service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class PasswordController {

    @PostMapping("/forgot-password")
    public ResponseEntity< ? > forgotPassword() {
        return null;
    }

    @PostMapping("/reset-password")
    public ResponseEntity< ? > resetPassword() {
        return null;
    }
}
