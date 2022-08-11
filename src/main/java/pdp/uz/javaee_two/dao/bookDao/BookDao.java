package pdp.uz.javaee_two.dao.bookDao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pdp.uz.javaee_two.configs.HibernateConfigurer;
import pdp.uz.javaee_two.dao.Dao;
import pdp.uz.javaee_two.domains.Book;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDao implements Dao {
    private static BookDao instance;

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    public Book findByBookName(String name) {

        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t where t.name = :name and t.status='ACTIVE'", Book.class);
        Book book = query.setParameter("name", name).getSingleResultOrNull();
        currentSession.getTransaction().commit();
        currentSession.close();
        return book;
    }

    public void save(Book book) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.persist(book);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public List<Book> findAll(String search) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t where (lower(t.name) like lower(:query) or lower(t.author) like lower(:query) or lower(t.description) like lower(:query)) and t.status = 'ACTIVE'", Book.class);
        query.setParameter("query", "%" + search + "%");
        List<Book> books = query.getResultList();
        currentSession.getTransaction().commit();
        currentSession.close();
        return books;
    }
    public List<Book> findAllPenning(String search) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t where (lower(t.name) like lower(:query) or lower(t.author) like lower(:query) or lower(t.description) like lower(:query)) and t.status = 'PENDING'", Book.class);
        query.setParameter("query", "%" + search + "%");
        List<Book> books = query.getResultList();
        currentSession.getTransaction().commit();
        currentSession.close();
        return books;
    }

    public List<Book> viewAllBooks(int i, int recordsPerPage, String search) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t where (lower(t.name) like lower(:query) or lower(t.author) like lower(:query) or lower(t.description) like lower(:query)) and t.status = 'ACTIVE'", Book.class);
        query.setParameter("query", "%" + search + "%");
        query.setFirstResult(i);
        query.setMaxResults(recordsPerPage);
        List<Book> books = query.getResultList();
        currentSession.getTransaction().commit();
        currentSession.close();
        return books;
    }
    public List<Book> viewAllPenningBooks(int i, int recordsPerPage, String search) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t where (lower(t.name) like lower(:query) or lower(t.author) like lower(:query) or lower(t.description) like lower(:query)) and t.status = 'PENDING'", Book.class);
        query.setParameter("query", "%" + search + "%");
        query.setFirstResult(i);
        query.setMaxResults(recordsPerPage);
        List<Book> books = query.getResultList();
        currentSession.getTransaction().commit();
        currentSession.close();
        return books;
    }
    public void update(Book book) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.merge(book);
        currentSession.getTransaction().commit();
        currentSession.close();
    }
    public Book findByBookId(Integer bookId) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t where t.id = :bookId", Book.class);
        Book book = query.setParameter("bookId", bookId).getSingleResultOrNull();
        currentSession.getTransaction().commit();
        currentSession.close();
        return book;
    }
}
