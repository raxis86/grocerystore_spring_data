package grocerystore.domain.abstracts;

import grocerystore.domain.entityes.Role;
import grocerystore.domain.exceptions.RoleException;

import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 13.01.2017.
 */
public interface IRepositoryRole extends IRepository<Role,UUID> {
    public Role roleByRoleName(String roleName) throws RoleException;
    public List<Role> getAllByUserId(UUID id) throws RoleException;
}
