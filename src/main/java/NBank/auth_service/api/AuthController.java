package NBank.auth_service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity< ? > login() {
        return null;
    }

    @PostMapping("/refresh")
    public ResponseEntity< ? > refresh() {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity< ? > logout() {
        return null;
    }

    @GetMapping("/validate")
    public ResponseEntity< ? > validate() {
        return null;
    }
}
