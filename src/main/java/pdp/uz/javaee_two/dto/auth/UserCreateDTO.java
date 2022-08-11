package pdp.uz.javaee_two.dto.auth;

import lombok.*;

/**
 * @author "Tojaliyev Asliddin"
 * @since 13/07/22 15:49 (Wednesday)
 * JavaEE_two/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
