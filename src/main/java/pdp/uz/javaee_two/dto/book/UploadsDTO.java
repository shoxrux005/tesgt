package pdp.uz.javaee_two.dto.book;

import lombok.Builder;
import lombok.Data;

/**
 * @author "Tojaliyev Asliddin"
 * @since 15/07/22 16:28 (Friday)
 * JavaEE_two/IntelliJ IDEA
 */
@Data
@Builder
public class UploadsDTO {
    private Long id;
    private String originalName;
    private String generatedName;
    private String contentType;
    private String path;
    private long size;
}
