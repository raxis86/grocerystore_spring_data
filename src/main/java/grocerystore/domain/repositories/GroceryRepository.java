package grocerystore.domain.repositories;

import grocerystore.domain.entityes.Grocery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by raxis on 19.01.2017.
 */
public interface GroceryRepository extends CrudRepository<Grocery,UUID>/*PagingAndSortingRepository<Grocery,UUID>*/ {
    @Override
    Grocery findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    Iterable<Grocery> findAll();

    @Override
    Iterable<Grocery> findAll(Iterable<UUID> uuids);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(Grocery entity);

    @Override
    void delete(Iterable<? extends Grocery> entities);

    @Override
    void deleteAll();
}
