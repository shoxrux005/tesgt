package pdp.uz.javaee_two.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.javaee_two.enums.UserStatus;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;


    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.USER;
}
