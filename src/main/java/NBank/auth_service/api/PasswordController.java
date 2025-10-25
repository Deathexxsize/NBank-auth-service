package NBank.auth_service.api;

import NBank.auth_service.dto.passwordDTOs.*;
import NBank.auth_service.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class PasswordController {
    private final PasswordService passwordService;

    @PostMapping("/forgot-password")
    public ResponseEntity< ? > forgotPassword(@RequestBody ForgotRequest request) {
        passwordService.forgotPassword(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-code")
    public ResponseEntity<VerifyCodeResponse> verifyCode(@RequestBody VerifyCodeRequest request) {
        return ResponseEntity.ok(passwordService.verifyCode(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResetResponse> resetPassword(@RequestBody ResetRequest request) {
        return ResponseEntity.ok(passwordService.resetPassword(request));
    }
}
