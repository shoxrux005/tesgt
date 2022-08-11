package pdp.uz.javaee_two.exception;

import javax.servlet.ServletException;

public class InvalidInputException extends ServletException {
    public InvalidInputException(String message) {
        super(message);
    }
}
