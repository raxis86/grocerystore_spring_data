package grocerystore.domain.repositories;

import grocerystore.domain.entityes.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by raxis on 19.01.2017.
 */
public interface RoleRepository extends CrudRepository<Role,UUID> {
    @Override
    <S extends Role> S save(S entity);

    @Override
    <S extends Role> Iterable<S> save(Iterable<S> entities);

    @Override
    Role findOne(UUID uuid);

    Role findByRoleName(String roleName);

    @Override
    boolean exists(UUID uuid);

    @Override
    Iterable<Role> findAll();

    @Override
    Iterable<Role> findAll(Iterable<UUID> uuids);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(Role entity);

    @Override
    void delete(Iterable<? extends Role> entities);

    @Override
    void deleteAll();
}
