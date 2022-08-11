package pdp.uz.javaee_two.dto.book;

import jakarta.persistence.OneToOne;
import lombok.*;
import pdp.uz.javaee_two.domains.Uploads;

import javax.servlet.http.Part;

/**
 * @author "Tojaliyev Asliddin"
 * @since 13/07/22 15:51 (Wednesday)
 * JavaEE_two/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDTO {
    private String name;
    private String description;
    private String author;
    private String genre;
    private String language;
    private Integer pageCount;

    private Part cover;
    private Part file;
}
