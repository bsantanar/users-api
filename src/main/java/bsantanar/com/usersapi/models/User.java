package bsantanar.com.usersapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @PrePersist
    public void prePersist() {
        userId = UUID.randomUUID();
        created = LocalDateTime.now(ZoneOffset.UTC);
        modified = LocalDateTime.now(ZoneOffset.UTC);
        lastLogin = LocalDateTime.now(ZoneOffset.UTC);
        isActive = true;
    }

    @PreUpdate
    public void preUpdate() {
        modified = LocalDateTime.now(ZoneOffset.UTC);
    }

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "token")
    private String token;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modified;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastLogin;

}
