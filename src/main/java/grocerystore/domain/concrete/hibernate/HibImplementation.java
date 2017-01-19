package grocerystore.domain.concrete.hibernate;

import grocerystore.tools.HibernateUtil;
import grocerystore.tools.JPAUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * Created by raxis on 17.01.2017.
 */
public class HibImplementation {

    protected static final SessionFactory factory = HibernateUtil.getSessionFactory();
    //protected static final EntityManager entityManager = factory.createEntityManager();

    //EntityManager entityManager = JPAUtil.getEntityManager();

}
