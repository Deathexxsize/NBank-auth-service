package NBank.auth_service.clients;

import NBank.auth_service.dto.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://user-service:8081/api/v1/users")
public interface UserClient {
    @PostMapping("/api/v1/users/profile")
    void createUserProfile(@RequestBody RegisterRequest request);
}
