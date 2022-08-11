package pdp.uz.javaee_two.domains;

import jakarta.persistence.*;
import lombok.*;
import pdp.uz.javaee_two.enums.BookStatus;
import pdp.uz.javaee_two.enums.Language;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private BookStatus status=BookStatus.PENDING;
    @Enumerated(EnumType.STRING)
    private Language language;
    private int pageCount;
    private int downloadCount;

    @OneToOne
    private Uploads cover;

    @OneToOne
    private Uploads file;


    @AllArgsConstructor
    @Getter
    public enum Genre {
        CRIME("Crime"),
        HORROR("Horror"),
        CI_FI("Ci-fi"),
        DRAMA("Drama"),
        ROMANCE("Romance"),
        ROMANCE_DRAMA("Romance Drama"),
        FANTASY("Fantasy");
        private final String key;

        public static Genre findByName(String genre) {
            return Arrays.stream(Genre.values()).filter(g->g.key.equals(genre)).findFirst().orElse(Genre.CRIME);
        }

        public String getKey() {
            return key;
        }
    }
}
