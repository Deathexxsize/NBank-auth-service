package NBank.auth_service.api;

import NBank.auth_service.dto.authDTOs.*;
import NBank.auth_service.model.UserPrincipal;
import NBank.auth_service.service.AuthService;
import NBank.auth_service.service.RefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshService refreshService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(
            @RequestBody RefreshRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(refreshService.refresh(request, userPrincipal.getId()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest request) {
        return ResponseEntity.ok(authService.logout(request));
    }
    // Пока что не вижу надобности для реализации
//    @GetMapping("/validate")
//    public ResponseEntity< ? > validate(@RequestHeader("Authorization") String authHeader) {
//        return null;
//    }
}
