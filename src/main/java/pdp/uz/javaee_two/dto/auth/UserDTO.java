package pdp.uz.javaee_two.dto.auth;

import lombok.*;
import pdp.uz.javaee_two.enums.UserStatus;

/**
 * @author "Tojaliyev Asliddin"
 * @since 13/07/22 15:50 (Wednesday)
 * JavaEE_two/IntelliJ IDEA
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserStatus status;
}
