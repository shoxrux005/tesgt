package pdp.uz.javaee_two.services.auth;

import lombok.RequiredArgsConstructor;
import pdp.uz.javaee_two.domains.User;
import pdp.uz.javaee_two.dto.auth.UserCreateDTO;
import pdp.uz.javaee_two.dto.auth.UserDTO;
import pdp.uz.javaee_two.dto.auth.UserLoginDTO;
import pdp.uz.javaee_two.dto.auth.UserUpdateDTO;



@RequiredArgsConstructor
public class UserService{

    public UserDTO create(UserCreateDTO userCreateDTO) {
        return null;
    }

    public User get(long id) {
        return null;
    }

    public void delete(long id) {

    }

    public void update(UserUpdateDTO userUpdateDTO) {

    }


    public UserDTO login(UserLoginDTO userLoginDTO) {
        return null;
    }
}
