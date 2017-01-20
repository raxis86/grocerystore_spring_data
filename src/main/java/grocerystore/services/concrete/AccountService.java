package grocerystore.services.concrete;

import grocerystore.domain.entityes.Role;
import grocerystore.domain.entityes.User;
import grocerystore.domain.models.Role_model;
import grocerystore.domain.models.User_model;
import grocerystore.domain.repositories.RoleRepository;
import grocerystore.domain.repositories.UserRepository;
import grocerystore.services.abstracts.IAccountService;
import grocerystore.services.exceptions.AccountServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static grocerystore.services.models.Converter.*;

/**
 * Created by raxis on 29.12.2016.
 * Сервис для регистрации и аутентификации пользователя
 */
@Service
public class AccountService implements IAccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private UserRepository userHandler;
    private RoleRepository roleHandler;

    public AccountService(UserRepository userHandler, RoleRepository roleHandler){
        this.userHandler=userHandler;
        this.roleHandler=roleHandler;
    }

    /**
     * Метод для аутентификации пользователя
     * @param userModel user
     * @return true if Ok
     */
    @Override
    public boolean logIn(User_model userModel) throws AccountServiceException {
        /*realizing by Spring Security*/
        return true;
    }

    /**
     * Метод для регистрации пользователя
     * @param userModel user
     * @return true if Ok
     * @throws AccountServiceException service exception
     */
    @Override
    public boolean signIn(User_model userModel) throws AccountServiceException {
        try {
            List<Role> roleList = new ArrayList<>();
            for(Role_model role_model:userModel.getRoles()) {
                roleList.add(roleHandler.findOne(role_model.getId()));
            }
            User user = convert(userModel, roleList);
            userHandler.save(user);
        } catch (Exception e) {
            logger.error("cant signIn!",e);
            throw new AccountServiceException("Невозможно зарегистрировать пользователя!",e);
        }

        return true;
    }

}
