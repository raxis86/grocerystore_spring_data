package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryOrder;
import grocerystore.domain.entityes.Order;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.OrderException;
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
public class OrderHib extends HibImplementation implements IRepositoryOrder{
    private static final Logger logger = LoggerFactory.getLogger(OrderHib.class);

    @Override
    public List<Order> getAll() throws OrderException {
        List<Order> orderList;
        EntityManager entityManager = factory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> q = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = q.from(Order.class);
            TypedQuery<Order> query = entityManager.createQuery(q);
            orderList =  query.getResultList();
        }
        catch (Exception e){
            logger.error("cant getAll",e);
            throw new OrderException("Проблема с базой данных: невозможно получить записи из таблицы заказов!",e);
        }
        finally {
            entityManager.close();
        }

        return orderList;
    }

    @Override
    public Order getOne(UUID id) throws OrderException {
        Order order;
        EntityManager entityManager = factory.createEntityManager();
        try {
            order  = entityManager.find(Order.class, id);
        }
        catch (Exception e){
            logger.error("Cant getOne Order_model!", e);
            throw new OrderException("Проблема с базой данных: невозможно получить запись из таблицы заказов!",e);
        }
        finally {
            entityManager.close();
        }
        return order;
    }

    @Override
    public boolean create(Order entity) throws OrderException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(entity);

            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant create",e);
            throw new OrderException("Проблема с базой данных: невозможно создать запись в таблице заказов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean delete(UUID id) throws OrderException {
        EntityManager entityManager = factory.createEntityManager();

        Order order = entityManager.find(Order.class, id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(order);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant delete",e);
            throw new OrderException("Проблема с базой данных: невозможно удалить запись из таблицы заказов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean update(Order entity) throws OrderException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant update",e);
            throw new OrderException("Проблема с базой данных: невозможно изменить запись в таблице заказов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public List<Order> getByUserId(UUID userid) throws OrderException {
        List<Order> orderList;
        EntityManager entityManager = factory.createEntityManager();

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> q = criteriaBuilder.createQuery(Order.class);
            Root<Order> lge = q.from(Order.class);

            q.select(lge).where(criteriaBuilder.equal(lge.get("userid"),userid));

            TypedQuery<Order> query = entityManager.createQuery(q);

            orderList =  query.getResultList();

        }
        catch (Exception e){
            logger.error("cant getByUserId",e);
            throw new OrderException("Проблема с базой данных: невозможно получить записи из таблицы заказов!",e);
        }
        finally {
            entityManager.close();
        }

        return orderList;
    }
}
