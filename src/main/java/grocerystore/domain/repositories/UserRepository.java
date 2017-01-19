package grocerystore.domain.repositories;

import grocerystore.domain.entityes.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by raxis on 19.01.2017.
 */
public interface UserRepository extends CrudRepository<User,UUID>{
    @Override
    User findOne(UUID uuid);

    User findOneByEmail(String email);

    User findOneByEmailAndPassword(String email, String password);

    @Override
    boolean exists(UUID uuid);

    @Override
    Iterable<User> findAll();

    @Override
    Iterable<User> findAll(Iterable<UUID> uuids);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(User entity);

    @Override
    void delete(Iterable<? extends User> entities);

    @Override
    void deleteAll();
}
