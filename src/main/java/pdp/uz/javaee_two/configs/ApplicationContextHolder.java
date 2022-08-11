package pdp.uz.javaee_two.configs;

import pdp.uz.javaee_two.dao.Dao;
import pdp.uz.javaee_two.dao.bookDao.BookDao;
import pdp.uz.javaee_two.dao.fileDao.FileDao;
import pdp.uz.javaee_two.dao.userDao.UserDao;
import pdp.uz.javaee_two.services.book.BookService;
import pdp.uz.javaee_two.services.file.FileUploadsService;

public class ApplicationContextHolder {
    public static <T> T getBean(Class<? extends Dao> clazz) {
        return switch (clazz.getSimpleName()) {
            case "UserDao" -> (T) UserDao.getInstance();
            case "BookDao" -> (T) BookDao.getInstance();
            case "FileDao" -> (T) FileDao.getInstance();
            case "BookService" -> (T) BookService.getInstance();
            case "FileUploadsService" -> (T) FileUploadsService.getInstance();

            default -> throw new BeanNotFoundException("Bean not found");
        };
    }

    public static class BeanNotFoundException extends RuntimeException {
        public BeanNotFoundException(String message) {
            super(message);
        }
    }
}

