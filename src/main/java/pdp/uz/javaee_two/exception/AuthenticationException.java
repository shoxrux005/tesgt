package pdp.uz.javaee_two.exception;

import javax.servlet.ServletException;



public class AuthenticationException extends ServletException {
    public AuthenticationException(String message) {
        super(message);
    }
}
