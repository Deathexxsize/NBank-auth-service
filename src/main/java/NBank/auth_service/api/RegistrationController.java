package NBank.auth_service.api;

import NBank.auth_service.dto.RegisterRequest;
import NBank.auth_service.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class RegistrationController {
    private final RegistrationService registerService;

    @PostMapping("/register")
    public ResponseEntity< ? > register(
            @RequestBody RegisterRequest request
            ) {
        registerService.userRegister(request);
        return ResponseEntity.ok().build();
    }
}
