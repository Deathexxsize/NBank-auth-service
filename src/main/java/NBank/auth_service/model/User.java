package NBank.auth_service.model;

import NBank.auth_service.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(length = 128, nullable = false)
    private String email;
    @Column(name = "phone_number", length = 32, nullable = false)
    private Long phoneNumber;
    @Column(nullable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role roles = Role.USED;
    @Column(name = "is_enable", nullable = false)
    private Boolean enable = true;
    @Column(name = "is_locked", nullable = false)
    private Boolean locked = false;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    private List<RefreshToken> refreshTokens = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<VerificationToken> verificationTokens = new ArrayList<>();
}
