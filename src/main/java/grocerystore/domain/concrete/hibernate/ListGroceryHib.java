package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryListGrocery;
import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.ListGroceryException;
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
public class ListGroceryHib extends HibImplementation implements IRepositoryListGrocery{
    private static final Logger logger = LoggerFactory.getLogger(ListGroceryHib.class);

    @Override
    public List<ListGrocery> getAll() throws DAOException {
        List<ListGrocery> listGroceryList;
        EntityManager entityManager = factory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ListGrocery> q = criteriaBuilder.createQuery(ListGrocery.class);
            Root<ListGrocery> root = q.from(ListGrocery.class);
            TypedQuery<ListGrocery> query = entityManager.createQuery(q);

            listGroceryList =  query.getResultList();
        }
        catch (Exception e){
            logger.error("Cant getall",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить записи из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return listGroceryList;
    }

    @Override
    public ListGrocery getOne(UUID id) throws DAOException {
        ListGrocery listGrocery;
        EntityManager entityManager = factory.createEntityManager();

        try {
            listGrocery = entityManager.find(ListGrocery.class, id);
        }
        catch (Exception e){
            logger.error("Cant getOne ListGrocery_model!", e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить запись из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }
        return listGrocery;
    }

    @Override
    public boolean create(ListGrocery entity) throws DAOException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant create",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно создать запись в таблице связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean delete(UUID id) throws DAOException {
        EntityManager entityManager = factory.createEntityManager();

        ListGrocery listGrocery = entityManager.find(ListGrocery.class, id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(listGrocery);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant delete",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно удалить запись из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public boolean update(ListGrocery entity) throws DAOException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant update",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно изменить запись в таблице связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public List<ListGrocery> getListById(UUID id) throws ListGroceryException {
        List<ListGrocery> listGrocery;

        EntityManager entityManager = factory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ListGrocery> q = criteriaBuilder.createQuery(ListGrocery.class);
            Root<ListGrocery> lge = q.from(ListGrocery.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("id"),id));


            TypedQuery<ListGrocery> query = entityManager.createQuery(q);

            listGrocery =  query.getResultList();
        }
        catch (Exception e){
            logger.error("cant getListById",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить записи из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return listGrocery;
    }

    @Override
    public List<ListGrocery> getListByGroceryId(UUID id) throws ListGroceryException {
        List<ListGrocery> listGrocery;
        EntityManager entityManager = factory.createEntityManager();

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ListGrocery> q = criteriaBuilder.createQuery(ListGrocery.class);
            Root<ListGrocery> lge = q.from(ListGrocery.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("groceryid"),id));

            TypedQuery<ListGrocery> query = entityManager.createQuery(q);

            listGrocery =  query.getResultList();

        }
        catch (Exception e){
            logger.error("cant getListByGroceryId",e);
            throw new ListGroceryException("Проблема с базой данных: невозможно получить записи из таблицы связанных продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return listGrocery;
    }

}
