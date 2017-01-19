package grocerystore.services.concrete;

import grocerystore.domain.abstracts.IRepositoryRole;
import grocerystore.domain.abstracts.IRepositoryUser;
import grocerystore.domain.entityes.Role;
import grocerystore.domain.entityes.User;
import grocerystore.domain.models.Role_model;
import grocerystore.domain.models.User_model;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.RoleException;
import grocerystore.domain.exceptions.UserException;
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

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 29.12.2016.
 */
@Service
public class UserService implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private IRepositoryUser userHandler;
    private IRepositoryRole roleHandler;
    private IValidator nameValidator;
    private IValidator addressValidator;
    private IValidator passwordValidator;
    private IValidator emailValidator;

    public UserService(IRepositoryUser userHandler,
                       IRepositoryRole roleHandler,
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
        User_model userModelByEmail;
        Role_model roleModelByName;
        List<Role_model> roleModelList = new ArrayList<>();

        try {
            roleModelByName = convert(roleHandler.roleByRoleName(roleName));
            roleModelList.add(roleModelByName);
            userModelByEmail = convert(userHandler.getOneByEmail(email));
        } catch (UserException e) {
            logger.error("cant getOneByEmail",e);
            throw new UserServiceException("Невозможно определить пользователя!",e);
        } catch (RoleException e) {
            logger.error("cant role by name",e);
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

        if(userModelByEmail !=null){
            message.addErrorMessage("Пользователь с таким email уже существует в базе!");
        }

        if(roleModelByName ==null){
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
        User_model userModelByEmail;

        try {
            userModelByEmail = convert(userHandler.getOneByEmail(email));
        } catch (UserException e) {
            logger.error("cant getOneByEmail",e);
            throw new UserServiceException("Невозможно проверить пользователя!",e);
        }

        if(userModelByEmail ==null){
            message.addErrorMessage("Пользователь с таким email не найден!");
            throw new FormUserException(message);
        }

        try {
            User user = userHandler.getOne(email.toLowerCase(), Tool.computeHash(password));
            if(user!=null){
                userModel = convert(user);
                userModel.setRoles(convertRoleList(user.getRoles()));
            }
        } catch (UserException e) {
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
            User user = userHandler.getOneByEmail(email);
            if(user!=null){
                userModel = convert(user);
                userModel.setRoles(convertRoleList(user.getRoles()));
            }
        } catch (UserException e) {
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
            userHandler.update(convert(userModel));
        } catch (DAOException e) {
            logger.error("cant update userModel",e);
            throw new UserServiceException("Невозможно сохранить изменения!",e);
        }
    }

    private User_model convert(User user){
        User_model user_model = new User_model();
        user_model.setId(user.getId());
        user_model.setEmail(user.getEmail());
        user_model.setStatus(User_model.Status.valueOf(user.getStatus()));
        user_model.setPassword(user.getPassword());
        user_model.setName(user.getName());
        user_model.setLastname(user.getLastname());
        user_model.setSurname(user.getSurname());
        user_model.setAddress(user.getAddress());
        user_model.setPhone(user.getPhone());

        return user_model;
    }

    private List<Role_model> convertRoleList(List<Role> roleList){
        List<Role_model> role_modelList = new ArrayList<>();
        for(Role role:roleList){
            role_modelList.add(convert(role));
        }

        return role_modelList;
    }

    private Role_model convert(Role role){
        Role_model role_model = new Role_model();
        role_model.setId(role.getId());
        role_model.setRoleName(role.getRoleName());

        return role_model;
    }

    private List<User_model> convertUserList(List<User> userList){
        List<User_model> user_modelList = new ArrayList<>();
        for(User u:userList){
            user_modelList.add(convert(u));
        }
        return user_modelList;
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
