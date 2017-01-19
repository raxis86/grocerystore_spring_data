package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryOrderStatus;
import grocerystore.domain.entityes.OrderStatus;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.OrderStatusException;
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
public class OrderStatusHib extends HibImplementation implements IRepositoryOrderStatus {
    private static final Logger logger = LoggerFactory.getLogger(OrderStatusHib.class);

    @Override
    public List<OrderStatus> getAll() throws OrderStatusException {
        List<OrderStatus> orderStatusList;
        EntityManager entityManager = factory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<OrderStatus> q = criteriaBuilder.createQuery(OrderStatus.class);
            Root<OrderStatus> root = q.from(OrderStatus.class);
            TypedQuery<OrderStatus> query = entityManager.createQuery(q);
            orderStatusList =  query.getResultList();
        }
        catch (Exception e){
            logger.error("cant gelAll",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно получить записи из таблицы статусов!",e);
        }
        finally {
            entityManager.close();
        }

        return orderStatusList;
    }

    @Override
    public OrderStatus getOne(UUID id) throws OrderStatusException {
        OrderStatus orderStatus;
        EntityManager entityManager = factory.createEntityManager();
        try {
            orderStatus  = entityManager.find(OrderStatus.class, id);
        }
        catch (Exception e){
            logger.error("Cant getOne OrderStatusSql!", e);
            throw new OrderStatusException("Проблема с базой данных: невозможно получить запись из таблицы статусов!",e);
        }
        finally {
            entityManager.close();
        }
        return orderStatus;
    }

    @Override
    public boolean create(OrderStatus entity) throws OrderStatusException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(entity);

            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant create",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно создать запись в таблице статусов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean delete(UUID id) throws OrderStatusException {
        EntityManager entityManager = factory.createEntityManager();

        OrderStatus orderStatus = entityManager.find(OrderStatus.class, id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(orderStatus);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant delete",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно удалить запись в таблице статусов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean update(OrderStatus entity) throws OrderStatusException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant update",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно изменить запись в таблице статусов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }
}
