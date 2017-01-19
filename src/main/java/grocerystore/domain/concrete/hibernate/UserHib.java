package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryUser;
import grocerystore.domain.entityes.Role;
import grocerystore.domain.entityes.User;
import grocerystore.domain.exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 18.01.2017.
 */
@Repository
public class UserHib extends HibImplementation implements IRepositoryUser{
    private static final Logger logger = LoggerFactory.getLogger(UserHib.class);

    @Override
    public List<User> getAll() throws UserException {
        List<User> userList;
        EntityManager entityManager = factory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> q = criteriaBuilder.createQuery(User.class);
            Root<User> root = q.from(User.class);
            TypedQuery<User> query = entityManager.createQuery(q);
            userList =  query.getResultList();
        }
        catch (Exception e){
            logger.error("cant fillUserRoleList",e);
            throw new UserException("Проблема с базой данных: невозможно получить записи из таблицы пользователей!",e);
        }
        finally {
            entityManager.close();
        }

        return userList;
    }

    @Override
    public User getOne(UUID id) throws UserException {
        User user;
        EntityManager entityManager = factory.createEntityManager();
        try {
            user  = entityManager.find(User.class, id);
        }
        catch (Exception e){
            logger.error("Cant getOne User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public boolean create(User entity) throws UserException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(entity);

            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("Insert user error!", e);
            throw new UserException("Проблема с базой данных: невозможно создать запись в таблице пользователей!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean delete(UUID id) throws UserException {
        EntityManager entityManager = factory.createEntityManager();

        User user = entityManager.find(User.class, id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("Cant delete User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно удалить запись из таблицы пользователей!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean update(User entity) throws UserException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("Cant update User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно изменить запись в таблице пользователей!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public User getOne(String email, String passwordHash) throws UserException {
        User user;
        EntityManager entityManager = factory.createEntityManager();
        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> q = criteriaBuilder.createQuery(User.class);
            Root<User> lge = q.from(User.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("email"),email),
                                criteriaBuilder.equal(lge.get("password"),passwordHash));

            TypedQuery<User> query = entityManager.createQuery(q);

            user = query.getSingleResult();
        }
        catch (Exception e){
            logger.error("Cant getOne User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public User getOneByEmail(String email) throws UserException {
        User user;
        EntityManager entityManager = factory.createEntityManager();
        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> q = criteriaBuilder.createQuery(User.class);
            Root<User> lge = q.from(User.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("email"),email));

            TypedQuery<User> query = entityManager.createQuery(q);

            user = query.getSingleResult();
        }
        catch (Exception e){
            logger.error("Cant getOne User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public List<User> getAllByRoleId(UUID id) throws UserException {
        List<User> userList;
        EntityManager entityManager = factory.createEntityManager();
        try {
            userList = entityManager.find(Role.class, id).getUsers();
        }
        catch (Exception e){
            logger.error("Cant getAllByUserId(UUID id) Role_model!", e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        finally {
            entityManager.close();
        }
        return userList;
    }
}
