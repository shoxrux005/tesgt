package pdp.uz.javaee_two.dao.fileDao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pdp.uz.javaee_two.configs.HibernateConfigurer;
import pdp.uz.javaee_two.dao.Dao;
import pdp.uz.javaee_two.domains.Uploads;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileDao implements Dao {
    private static FileDao instance;

    public static FileDao getInstance() {
        if (instance == null) {
            instance = new FileDao();
        }
        return instance;
    }

    public void save(Uploads uploads) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.persist(uploads);
        currentSession.getTransaction().commit();
        currentSession.close();
    }
}
