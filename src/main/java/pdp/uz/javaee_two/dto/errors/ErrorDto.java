package pdp.uz.javaee_two.dto.errors;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author "Tojaliyev Asliddin"
 * @since 12/07/22 22:51 (Tuesday)
 * JavaEE_two/IntelliJ IDEA
 */
@Data
@Builder
public class ErrorDto {
    private String message;
    private String detailedMessage;
    private String path;
    @Builder.Default
    private Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());


}
