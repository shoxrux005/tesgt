package pdp.uz.javaee_two.dao.userDao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import pdp.uz.javaee_two.configs.HibernateConfigurer;
import pdp.uz.javaee_two.dao.Dao;
import pdp.uz.javaee_two.domains.User;
import pdp.uz.javaee_two.dto.auth.UserUpdateDTO;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public User findByUsername(String username) {

        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<User> query = currentSession.createQuery("select t from User t where t.username = :username", User.class);
        User user = query.setParameter("username", username).getSingleResultOrNull();
        currentSession.getTransaction().commit();
        currentSession.close();
        return user;
    }

    public void save(User user) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.persist(user);
        currentSession.getTransaction().commit();
        currentSession.close();
    }
    public void update(User user) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.merge(user);
        currentSession.getTransaction().commit();
        currentSession.close();
    }


}
