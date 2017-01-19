package grocerystore.services.abstracts;

import grocerystore.domain.models.User_model;
import grocerystore.services.exceptions.AccountServiceException;


/**
 * Created by raxis on 29.12.2016.
 */
public interface IAccountService {
    public boolean logIn(User_model userModel) throws AccountServiceException;
    public boolean signIn(User_model userModel) throws AccountServiceException;
}
