package pdp.uz.javaee_two.services.book;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.dao.Dao;
import pdp.uz.javaee_two.dao.bookDao.BookDao;
import pdp.uz.javaee_two.domains.Book;
import pdp.uz.javaee_two.domains.Uploads;
import pdp.uz.javaee_two.dto.book.BookCreateDTO;
import pdp.uz.javaee_two.enums.BookStatus;
import pdp.uz.javaee_two.enums.Language;
import pdp.uz.javaee_two.exception.InvalidInputException;
import pdp.uz.javaee_two.services.file.FileUploadsService;

import java.io.IOException;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookService implements Dao{


    private final FileUploadsService fileUploadsService=ApplicationContextHolder.getBean(FileUploadsService.class);
    private final BookDao bookDao = ApplicationContextHolder.getBean(BookDao.class);

    private static BookService instance;

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }


    public void create(BookCreateDTO dto) throws InvalidInputException, IOException {
//        Book bookByName=bookDao.findByBookName(dto.getName());
//        if (Objects.nonNull(bookByName)) {
//            throw new InvalidInputException("Book name already taken by '%s'".formatted(dto.getName()));
//        }



        Uploads cov=fileUploadsService.uploadCover(dto.getFile());
        Uploads fil=fileUploadsService.uploadFile(dto.getFile());

        Book book=Book.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .author(dto.getAuthor())
                .genre(Book.Genre.findByName(dto.getGenre()))
                .language(Language.findByName(dto.getLanguage()))
                .pageCount(dto.getPageCount())
                .cover(cov)
                .file(fil)
                .build();

        bookDao.save(book);
    }
    public void confirm(Integer bookId) throws InvalidInputException {
        Book book=bookDao.findByBookId(bookId);
        System.out.println("book = " + book);
        if (Objects.isNull(book)) {
            throw new InvalidInputException("Book not found");
        }
        book.setStatus(BookStatus.ACTIVE);
        bookDao.update(book);
    }
    public void delete(Integer bookId) throws InvalidInputException {
        Book book=bookDao.findByBookId(bookId);
        System.out.println("book = " + book);
        if (Objects.isNull(book)) {
            throw new InvalidInputException("Book not found");
        }
        book.setStatus(BookStatus.DELETED);
        bookDao.update(book);
    }
}
