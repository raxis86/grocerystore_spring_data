package grocerystore.domain.abstracts;

import grocerystore.domain.entityes.User;
import grocerystore.domain.exceptions.UserException;

import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 13.01.2017.
 */
public interface IRepositoryUser extends IRepository<User,UUID> {
    public User getOne(String email, String passwordHash) throws UserException;
    public User getOneByEmail(String email) throws UserException;
    public List<User> getAllByRoleId(UUID id) throws UserException;
}
