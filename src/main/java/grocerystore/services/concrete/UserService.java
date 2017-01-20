package grocerystore.services.concrete;

import grocerystore.domain.entityes.Role;
import grocerystore.domain.entityes.User;
import grocerystore.domain.models.Role_model;
import grocerystore.domain.models.User_model;
import grocerystore.domain.repositories.RoleRepository;
import grocerystore.domain.repositories.UserRepository;
import grocerystore.services.abstracts.IUserService;
import grocerystore.services.exceptions.FormUserException;
import grocerystore.services.exceptions.UserServiceException;
import grocerystore.services.exceptions.ValidateException;
import grocerystore.services.models.Message;
import grocerystore.services.validators.abstracts.IValidator;
import grocerystore.tools.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static grocerystore.services.models.Converter.*;

/**
 * Created by raxis on 29.12.2016.
 */
@Service
public class UserService implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userHandler;
    private RoleRepository roleHandler;
    private IValidator nameValidator;
    private IValidator addressValidator;
    private IValidator passwordValidator;
    private IValidator emailValidator;

    public UserService(UserRepository userHandler,
                       RoleRepository roleHandler,
                       IValidator nameValidator,
                       IValidator addressValidator,
                       IValidator passwordValidator,
                       IValidator emailValidator){
        this.userHandler=userHandler;
        this.roleHandler=roleHandler;
        this.nameValidator=nameValidator;
        this.addressValidator=addressValidator;
        this.passwordValidator=passwordValidator;
        this.emailValidator=emailValidator;
    }

    @Override
    public User_model formUser(String email, String password, String name,
                               String lastname, String surname, String address,
                               String phone, String roleName) throws UserServiceException, FormUserException {

        Message message = new Message();
        User_model userModel = new User_model();
        User userByEmail=null;
        Role roleByName=null;
        List<Role_model> roleModelList = new ArrayList<>();

        try {
            if(roleName!=null){
                roleByName = roleHandler.findByRoleName(roleName);
                roleModelList.add(convert(roleByName));
                userByEmail = userHandler.findOneByEmail(email);
            }
        } catch (Exception e) {
            logger.error("cant getOneByEmail",e);
            throw new UserServiceException("Невозможно определить пользователя!",e);
        }


        try {
            emailValidator.validate(email);
            passwordValidator.validate(password);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }

        try {
            nameValidator.validate(name);
            nameValidator.validate(lastname);
            nameValidator.validate(surname);
            addressValidator.validate(address);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }

        if(userByEmail !=null){
            message.addErrorMessage("Пользователь с таким email уже существует в базе!");
        }

        if(roleByName ==null){
            message.addErrorMessage("Роли с таким наименованием не существует!");
        }

        if(message.isOk()){
            userModel.setId(UUID.randomUUID());
            userModel.setEmail(email.toLowerCase());
            userModel.setPassword(Tool.computeHash(password));
            userModel.setStatus(User_model.Status.ACTIVE);
            userModel.setName(name);
            userModel.setLastname(lastname);
            userModel.setSurname(surname);
            userModel.setPhone(phone);
            userModel.setAddress(address);
            userModel.setRoles(roleModelList);
        }
        else {
            throw new FormUserException(message);
        }

        return userModel;
    }

    @Override
    public User_model formUserFromRepo(String email, String password) throws UserServiceException, FormUserException {
        //Ищем, что существует юзер с таким email
        Message message = new Message();
        User_model userModel=null;
        User userModelByEmail;

        try {
            userModelByEmail = userHandler.findOneByEmail(email);
        } catch (Exception e) {
            logger.error("cant getOneByEmail",e);
            throw new UserServiceException("Невозможно проверить пользователя!",e);
        }

        if(userModelByEmail ==null){
            message.addErrorMessage("Пользователь с таким email не найден!");
            throw new FormUserException(message);
        }

        try {
            User user = userHandler.findOneByEmailAndPassword(email.toLowerCase(), Tool.computeHash(password));
            if(user!=null){
                userModel = convert(user);
                userModel.setRoles(convertRoleList(user.getRoles()));
            }
        } catch (Exception e) {
            logger.error("cant getOn",e);
            throw new UserServiceException("Невозможно определить пользователя!",e);
        }

        if(userModel ==null){
            message.addErrorMessage("Неверный пароль!");
            throw new FormUserException(message);
        }

        return userModel;
    }

    @Override
    public User_model formUserFromRepo(String email) throws UserServiceException, FormUserException {
        Message message = new Message();
        User_model userModel=null;

        try {
            User user = userHandler.findOneByEmail(email);
            if(user!=null){
                userModel = convert(user);
                userModel.setRoles(convertRoleList(user.getRoles()));
            }
        } catch (Exception e) {
            logger.error("cant getOneByEmail",e);
            throw new UserServiceException("Невозможно проверить пользователя!",e);
        }

        if(userModel ==null){
            message.addErrorMessage("Пользователь с таким email не найден!");
            throw new FormUserException(message);
        }

        return userModel;
    }

    @Override
    public void updateUser(User_model userModel, String name, String lastname,
                           String surname, String address, String phone) throws UserServiceException {
        try {
            nameValidator.validate(name);
            nameValidator.validate(lastname);
            addressValidator.validate(address);
        } catch (ValidateException e) {
            throw new UserServiceException(e.getMessage(),e);
        }
        userModel.setName(name);
        userModel.setLastname(lastname);
        userModel.setSurname(surname);
        userModel.setAddress(address);
        userModel.setPhone(phone);

        try {
            List<Role> roleList = new ArrayList<>();
            for(Role_model role_model:userModel.getRoles()) {
                roleList.add(roleHandler.findOne(role_model.getId()));
            }
            userHandler.save(convert(userModel,roleList));
        } catch (Exception e) {
            logger.error("cant update userModel",e);
            throw new UserServiceException("Невозможно сохранить изменения!",e);
        }
    }

}
