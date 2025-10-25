package NBank.auth_service.repository;

import NBank.auth_service.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> getRefreshTokenByToken(String token);
    boolean deleteRefreshTokenByToken(String token);
}
