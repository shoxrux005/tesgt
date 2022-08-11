package pdp.uz.javaee_two.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.javaee_two.dto.auth.UserDTO;
import pdp.uz.javaee_two.enums.UserStatus;

import java.util.Objects;

/**
 * @author "Tojaliyev Asliddin"
 * @since 13/07/22 15:51 (Wednesday)
 * JavaEE_two/IntelliJ IDEA
 */
public class Session {
    public static SessionUser sessionUser;

    public static void setSessionUser(UserDTO user) {
        if (Objects.isNull(user)) {
            sessionUser = null;
        } else
            sessionUser = new SessionUser(user);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionUser {
        private Long id;
        private String email;
        private UserStatus status;

        public SessionUser(UserDTO userDTO) {
            this.id = userDTO.getId();
            this.email = userDTO.getUsername();
            this.email = userDTO.getPassword();
            this.status = userDTO.getStatus();
        }
    }
}
