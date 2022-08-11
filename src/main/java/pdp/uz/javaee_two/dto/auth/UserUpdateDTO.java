package pdp.uz.javaee_two.dto.auth;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String password;
}
