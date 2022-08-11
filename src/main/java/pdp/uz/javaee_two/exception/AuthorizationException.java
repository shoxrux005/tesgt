package pdp.uz.javaee_two.exception;

import javax.servlet.ServletException;


public class AuthorizationException extends ServletException {
    public AuthorizationException(String message) {
        super(message);
    }
}
