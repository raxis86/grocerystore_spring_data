package grocerystore.services.abstracts;

import grocerystore.domain.models.User_model;
import grocerystore.services.exceptions.FormUserException;
import grocerystore.services.exceptions.UserServiceException;

/**
 * Created by raxis on 29.12.2016.
 */
public interface IUserService {
    public User_model formUser(String email, String password, String name,
                               String lastname, String surname,
                               String address, String phone, String role) throws UserServiceException, FormUserException;
    public User_model formUserFromRepo(String email, String password) throws UserServiceException, FormUserException;
    public User_model formUserFromRepo(String email) throws UserServiceException, FormUserException;
    public void updateUser(User_model userModel, String name, String lastname,
                           String surname, String address, String phone) throws UserServiceException;
}
