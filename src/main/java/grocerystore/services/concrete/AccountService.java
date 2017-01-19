package grocerystore.services.concrete;

import grocerystore.domain.abstracts.IRepositoryRole;
import grocerystore.domain.abstracts.IRepositoryUser;
import grocerystore.domain.entityes.Role;
import grocerystore.domain.entityes.User;
import grocerystore.domain.models.Role_model;
import grocerystore.domain.models.User_model;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.services.abstracts.IAccountService;
import grocerystore.services.exceptions.AccountServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raxis on 29.12.2016.
 * Сервис для регистрации и аутентификации пользователя
 */
@Service
public class AccountService implements IAccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private IRepositoryUser userHandler;
    private IRepositoryRole roleHandler;

    public AccountService(IRepositoryUser userHandler, IRepositoryRole roleHandler){
        this.userHandler=userHandler;
        this.roleHandler=roleHandler;
    }

    /**
     * Метод для аутентификации пользователя
     * @param userModel
     * @return
     */
    @Override
    public boolean logIn(User_model userModel) throws AccountServiceException {
        /*Role_model role = null;
        try {
            role = roleHandler.getOne(userModel.getRoles().get(0));
        } catch (DAOException e) {
            logger.error("cant logIn",e);
            throw new AccountServiceException("Невозможно осуществить вход в систему!",e);
        }
        return new AuthUser(userModel,role);*/
        return true;
    }

    /**
     * Метод для регистрации пользователя
     * @param userModel
     * @return
     * @throws DAOException
     */
    @Override
    public boolean signIn(User_model userModel) throws AccountServiceException {
        try {
            //List<Role> roleList = new ArrayList<>();
            User user = convert(userModel);
            /*Role role = roleHandler.getOne(userModel.getRoles().get(0).getId());
            roleList.add(role);
            user.setRoles(roleList);*/
            userHandler.create(user);
        } catch (DAOException e) {
            logger.error("cant signIn!",e);
            throw new AccountServiceException("Невозможно зарегистрировать пользователя!",e);
        }

        return true;
    }

    private User convert(User_model user_model) throws DAOException {
        User user = new User();
        user.setId(user_model.getId());
        user.setEmail(user_model.getEmail());
        user.setPassword(user_model.getPassword());
        user.setStatus(user_model.getStatus().toString());
        user.setName(user_model.getName());
        user.setLastname(user_model.getLastname());
        user.setSurname(user_model.getSurname());
        user.setPhone(user_model.getPhone());
        user.setAddress(user_model.getAddress());

        List<Role> roleList = new ArrayList<>();
        for(Role_model role_model:user_model.getRoles()){
            roleList.add(roleHandler.getOne(role_model.getId()));
        }
        user.setRoles(roleList);

        return user;
    }

}
