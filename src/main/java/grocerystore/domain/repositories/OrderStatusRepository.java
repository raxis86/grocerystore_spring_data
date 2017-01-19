package grocerystore.domain.repositories;

import grocerystore.domain.entityes.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by raxis on 19.01.2017.
 */
public interface OrderStatusRepository extends CrudRepository<OrderStatus,UUID> {
    @Override
    <S extends OrderStatus> S save(S entity);

    @Override
    <S extends OrderStatus> Iterable<S> save(Iterable<S> entities);

    @Override
    OrderStatus findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    Iterable<OrderStatus> findAll();

    @Override
    Iterable<OrderStatus> findAll(Iterable<UUID> uuids);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(OrderStatus entity);

    @Override
    void delete(Iterable<? extends OrderStatus> entities);

    @Override
    void deleteAll();
}
