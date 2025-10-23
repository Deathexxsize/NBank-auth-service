package NBank.auth_service.api;

import NBank.auth_service.dto.LoginRequest;
import NBank.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity< ? > login(
            @RequestBody LoginRequest request
            ) {
        authService.login(request);
        return ResponseEntity.ok().build();
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
