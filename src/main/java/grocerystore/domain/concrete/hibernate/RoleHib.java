package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryRole;
import grocerystore.domain.entityes.Role;
import grocerystore.domain.entityes.User;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.RoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 18.01.2017.
 */
@Repository
public class RoleHib extends HibImplementation implements IRepositoryRole {
    private static final Logger logger = LoggerFactory.getLogger(RoleHib.class);

    @Override
    public List<Role> getAll() throws RoleException {
        List<Role> roleList;
        EntityManager entityManager = factory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> q = criteriaBuilder.createQuery(Role.class);
            Root<Role> root = q.from(Role.class);
            TypedQuery<Role> query = entityManager.createQuery(q);
            roleList =  query.getResultList();
        }
        catch (Exception e){
            logger.error("cant getAll",e);
            throw new RoleException("Проблема с базой данных: невозможно получить записи из таблицы ролей!",e);
        }
        finally {
            entityManager.close();
        }
        return roleList;
    }

    @Override
    public Role getOne(UUID id) throws RoleException {
        Role role;
        EntityManager entityManager = factory.createEntityManager();
        try {
            role  = entityManager.find(Role.class, id);
        }
        catch (Exception e){
            logger.error("Cant getOne Role_model!", e);
            throw new RoleException("Проблема с базой данных: невозможно получить запись из таблицы ролей!",e);
        }
        finally {
            entityManager.close();
        }
        return role;
    }

    @Override
    public boolean create(Role entity) throws RoleException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(entity);

            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant create",e);
            throw new RoleException("Проблема с базой данных: невозможно создать запись в таблице ролей!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean delete(UUID id) throws RoleException {
        EntityManager entityManager = factory.createEntityManager();

        Role role = entityManager.find(Role.class, id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(role);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant delete",e);
            throw new RoleException("Проблема с базой данных: невозможно удалить запись из таблицы ролей!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean update(Role entity) throws RoleException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant update",e);
            throw new RoleException("Проблема с базой данных: невозможно изменить запись в таблице ролей!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public Role roleByRoleName(String roleName) throws RoleException {
        Role role;
        EntityManager entityManager = factory.createEntityManager();
        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> q = criteriaBuilder.createQuery(Role.class);
            Root<Role> lge = q.from(Role.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("rolename"),roleName));

            TypedQuery<Role> query = entityManager.createQuery(q);

            role = query.getSingleResult();
        }
        catch (Exception e){
            logger.error("Cant getOne Role_model!", e);
            throw new RoleException("Проблема с базой данных: невозможно получить запись из таблицы ролей!",e);
        }
        finally {
            entityManager.close();
        }
        return role;
    }

    @Override
    public List<Role> getAllByUserId(UUID id) throws RoleException {
        List<Role> roleList;
        EntityManager entityManager = factory.createEntityManager();
        try {
            roleList = entityManager.find(User.class, id).getRoles();
        }
        catch (Exception e){
            logger.error("Cant getAllByUserId(UUID id) Role_model!", e);
            throw new RoleException("Проблема с базой данных: невозможно получить запись из таблицы ролей!",e);
        }
        finally {
            entityManager.close();
        }
        return roleList;
    }
}
