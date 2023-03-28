package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUser(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("Select u from User u where u.id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void deleteUser(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("DELETE FROM User WHERE id=:id");
        entityManager.getTransaction().begin();
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
