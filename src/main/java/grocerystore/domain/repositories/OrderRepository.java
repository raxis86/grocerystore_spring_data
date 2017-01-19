package grocerystore.domain.repositories;

import grocerystore.domain.entityes.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by raxis on 19.01.2017.
 */
public interface OrderRepository extends CrudRepository<Order,UUID> {
    @Override
    Order findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    Iterable<Order> findAll();

    Iterable<Order> findAllByUserid(UUID userid);

    @Override
    Iterable<Order> findAll(Iterable<UUID> uuids);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(Order entity);

    @Override
    void delete(Iterable<? extends Order> entities);

    @Override
    void deleteAll();
}
