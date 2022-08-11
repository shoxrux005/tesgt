package pdp.uz.javaee_two.exception;

import javax.servlet.ServletException;

public class NotFoundException extends ServletException {
    public NotFoundException(String message) {
        super(message);
    }
}
