package pdp.uz.javaee_two.dto.book;

import lombok.*;
import pdp.uz.javaee_two.enums.BookStatus;

/**
 * @author "Tojaliyev Asliddin"
 * @since 13/07/22 15:52 (Wednesday)
 * JavaEE_two/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUpdateDTO {
    private Long id;
    private String name;
    private String description;
    private int downloadCount;
    private BookStatus status;
}
