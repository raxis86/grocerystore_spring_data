package grocerystore.domain.concrete.hibernate;

import grocerystore.domain.abstracts.IRepositoryGrocery;
import grocerystore.domain.entityes.Grocery;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.GroceryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 18.01.2017.
 */
@Repository
public class GroceryHib extends HibImplementation implements IRepositoryGrocery {
    private static final Logger logger = LoggerFactory.getLogger(GroceryHib.class);

    @Override
    public List<Grocery> getAll() throws GroceryException {
        List<Grocery> groceriesEntityList;

        EntityManager entityManager = factory.createEntityManager();
        //EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Grocery> q = criteriaBuilder.createQuery(Grocery.class);
            Root<Grocery> root = q.from(Grocery.class);
            TypedQuery<Grocery> query = entityManager.createQuery(q);

            groceriesEntityList =  query.getResultList();

        }
        catch (Exception e){
            logger.error("cant getAll",e);
            throw new GroceryException("Проблема с базой данных: невозможно получить записи из таблицы продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return groceriesEntityList;
    }


    @Override
    public Grocery getOne(UUID id) throws GroceryException {
        Grocery grocery;
        EntityManager entityManager = factory.createEntityManager();

        try {
            grocery  = entityManager.find(Grocery.class, id);
        }
        catch (Exception e){
            logger.error("Cant getOne Grocery_model!", e);
            throw new GroceryException("Проблема с базой данных: невозможно получить запись из таблицы продуктов!",e);
        }
        finally {
            entityManager.close();
        }


        return grocery;
    }

    @Override
    public boolean create(Grocery entity) throws GroceryException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(entity);

            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant create",e);
            throw new GroceryException("Проблема с базой данных: невозможно создать запись в таблице продуктов!",e);
        }
        finally {
            entityManager.close();
        }


        return true;
    }

    @Override
    public boolean delete(UUID id) throws GroceryException {
        EntityManager entityManager = factory.createEntityManager();

        Grocery grocery = entityManager.find(Grocery.class, id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(grocery);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant delete",e);
            throw new GroceryException("Проблема с базой данных: невозможно удалить запись из таблицы продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean update(Grocery entity) throws GroceryException {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            logger.error("cant update",e);
            throw new GroceryException("Проблема с базой данных: невозможно изменить запись в таблице продуктов!",e);
        }
        finally {
            entityManager.close();
        }

        return true;
    }


   /* public static void main(String[] args) {
        try {
            List<Grocery> entities = new GroceryHib().getAll();
            for(Grocery g:entities){
                System.out.println(g.getName());
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(new GroceryHib().getOne(UUID.fromString("1150b23a-e004-44b2-aa6f-aec18ae69d41")).getName());
        } catch (GroceryException e) {
            e.printStackTrace();
        }

        *//*Grocery grocery = new Grocery();
        grocery.setId(UUID.randomUUID());
        grocery.setName("hibernate");
        grocery.setIscategory(false);
        grocery.setParentid(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        grocery.setQuantity(100);
        grocery.setPrice(new BigDecimal(125));

        try {
            new GroceryHibNew().create(grocery);
        } catch (GroceryException e) {
            e.printStackTrace();
        }*//*
    }*/
}
