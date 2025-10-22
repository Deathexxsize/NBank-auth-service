package NBank.auth_service.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", url = "http://user-service:8081/api/v1/users")
public interface UserClient {
    
}
