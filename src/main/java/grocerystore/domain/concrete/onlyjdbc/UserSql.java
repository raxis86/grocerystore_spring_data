/*
package grocerystore.domain.concrete.onlyjdbc;

import grocerystore.domain.abstracts.IRepositoryUser;
import grocerystore.domain.models.Role;
import grocerystore.domain.models.User;
import grocerystore.domain.exceptions.RoleException;
import grocerystore.domain.exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static grocerystore.constants.Constants.*;
*/
/**
 * Created by raxis on 13.01.2017.
 *//*

@Repository
public class UserSql extends SQLImplementation implements IRepositoryUser {
    private static final Logger logger = LoggerFactory.getLogger(UserSql.class);

    private void fillUser(User_model usr, ResultSet resultSet) throws SQLException, UserException {
        usr.setId(UUID.fromString(resultSet.getString("ID")));
        usr.setEmail(resultSet.getString("EMAIL"));
        usr.setPassword(resultSet.getString("PASSWORD"));
        usr.setStatus(User_model.Status.valueOf(resultSet.getString("STATUS")));
        usr.setName(resultSet.getString("NAME"));
        usr.setLastname(resultSet.getString("LASTNAME"));
        usr.setSurname(resultSet.getString("SURNAME"));
        usr.setAddress(resultSet.getString("ADDRESS"));
        usr.setPhone(resultSet.getString("PHONE"));

        RoleSql roleSecSql = new RoleSql();
        try {
            usr.setRoles(roleSecSql.getAllByUserId(UUID.fromString(resultSet.getString("ID"))));
        } catch (RoleException e) {
            logger.error("cant fillUserRoleList",e);
            throw new UserException("Проблема с базой данных: невозможно получить записи из таблицы пользователей!",e);
        }
    }

    @Override
    public List<User_model> getAll() throws UserException {
        List<User_model> userList = new ArrayList<>();
        try(Connection connection = getDs().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(USERSEC_SELECTALL_QUERY);) {
            while (resultSet.next()){
                User_model usr = new User_model();
                fillUser(usr,resultSet);
                userList.add(usr);
            }
        } catch (SQLException e) {
            logger.error("Select Users error!",e);
            throw new UserException("Проблема с базой данных: невозможно получить записи из таблицы пользователей!",e);
        }
        return userList;
    }

    @Override
    public User_model getOne(UUID id) throws UserException {
        User_model usr = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(USERSEC_PREP_SELECTONE_QUERY);) {
            statement.setObject(1,id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                usr = new User_model();
                fillUser(usr,resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        return usr;
    }

    @Override
    public boolean create(User_model entity) throws UserException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(USERSEC_PREP_INSERT_QUERY);) {
            statement.setObject(1,entity.getId().toString());
            statement.setObject(2,entity.getEmail());
            statement.setObject(3,entity.getPassword());
            statement.setObject(4,entity.getStatus().toString());
            statement.setObject(5,entity.getName());
            statement.setObject(6,entity.getLastname());
            statement.setObject(7,entity.getSurname());
            statement.setObject(8,entity.getAddress());
            statement.setObject(9,entity.getPhone());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Insert user error!", e);
            throw new UserException("Проблема с базой данных: невозможно создать запись в таблице пользователей!",e);
        }
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(USERSANDROLES_INSERT_QUERY);) {
            for(Role_model role:entity.getRoles()){
                statement.setObject(1,entity.getId().toString());
                statement.setObject(2,role.getId().toString());
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("Insert user error!", e);
            throw new UserException("Проблема с базой данных: невозможно создать запись в таблице usersandroles!",e);
        }
        return true;
    }

    @Override
    public boolean delete(UUID id) throws UserException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement1 = connection.prepareStatement(USERSEC_PREP_DELETE_QUERY);
            PreparedStatement statement2 = connection.prepareStatement(USERSANDROLES_DELETE_BY_USERID_QUERY);) {
            statement1.setObject(1,id.toString());
            statement1.execute();
            statement2.setObject(1,id.toString());
            statement2.execute();
        } catch (SQLException e) {
            logger.error("Cant delete User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно удалить запись из таблицы пользователей!",e);
        }

        return true;
    }

    @Override
    public boolean update(User_model entity) throws UserException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement=connection.prepareStatement(USERSEC_PREP_UPDATE_QUERY);) {
            statement.setObject(1,entity.getEmail());
            statement.setObject(2,entity.getPassword());
            statement.setObject(3,entity.getStatus().toString());
            statement.setObject(4,entity.getName());
            statement.setObject(5,entity.getLastname());
            statement.setObject(6,entity.getSurname());
            statement.setObject(7,entity.getAddress());
            statement.setObject(8,entity.getPhone());
            statement.setObject(9,entity.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant update User_model!",e);
            throw new UserException("Проблема с базой данных: невозможно изменить запись в таблице пользователей!",e);
        }
        return true;
    }

    @Override
    public User_model getOne(String email, String passwordHash) throws UserException {
        User_model usr = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(USERSEC_PREP_SELECTONE_BY_AUTH_QUERY)) {
            statement.setObject(1,email);
            statement.setObject(2,passwordHash);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                usr = new User_model();
                fillUser(usr,resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne User_model by auth info",e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        return usr;
    }

    @Override
    public User_model getOneByEmail(String email) throws UserException {
        User_model usr = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(USERSEC_PREP_SELECTONE_BY_EMAIL_QUERY)) {
            statement.setObject(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                usr = new User_model();
                fillUser(usr,resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne User_model by auth info",e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        return usr;
    }

    @Override
    public List<User_model> getAllByRoleId(UUID id) throws UserException {
        List<User_model> userList = new ArrayList<>();
        User_model usr = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(USERSEC_PREP_SELECTALL_BY_ROLEID_QUERY)) {
            statement.setObject(1,id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                usr = new User_model();
                fillUser(usr,resultSet);
                userList.add(usr);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getAllByUserId(UUID id) Role_model!", e);
            throw new UserException("Проблема с базой данных: невозможно получить запись из таблицы пользователей!",e);
        }
        return userList;
    }
}
*/
