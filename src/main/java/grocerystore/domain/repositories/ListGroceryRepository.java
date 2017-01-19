package grocerystore.domain.repositories;

import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.entityes.ListGroceryPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * Created by raxis on 19.01.2017.
 */
public interface ListGroceryRepository extends CrudRepository<ListGrocery,ListGroceryPK> {
    @Override
    <S extends ListGrocery> S save(S entity);

    @Override
    <S extends ListGrocery> Iterable<S> save(Iterable<S> entities);

    @Override
    ListGrocery findOne(ListGroceryPK listGroceryPK);

    @Override
    boolean exists(ListGroceryPK listGroceryPK);

    @Override
    Iterable<ListGrocery> findAll(Iterable<ListGroceryPK> listGroceryPKS);

    @Override
    long count();

    @Override
    void delete(ListGroceryPK listGroceryPK);

    @Override
    Iterable<ListGrocery> findAll();

    Iterable<ListGrocery> findAllById(UUID uuid);

    @Override
    void delete(ListGrocery entity);

    @Override
    void delete(Iterable<? extends ListGrocery> entities);

    @Override
    void deleteAll();

    /*@Query("select b from Bank b where b.name = :name")
    Iterable<ListGrocery> findAllByGroceryId(@Param("name") String name);*/
    Iterable<ListGrocery> findAllByGroceryid(UUID groceryid);
}
