/*
package grocerystore.domain.concrete.onlyjdbc;

import grocerystore.domain.abstracts.IRepositoryRole;
import grocerystore.domain.models.Role;
import grocerystore.domain.exceptions.RoleException;
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
public class RoleSql extends SQLImplementation implements IRepositoryRole {
    private static final Logger logger = LoggerFactory.getLogger(RoleSql.class);

    @Override
    public List<Role_model> getAll() throws RoleException {
        List<Role_model> roleList = new ArrayList<>();
        try(Connection connection = getDs().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(ROLESEC_SELECTALL_QUERY);) {
            while (resultSet.next()){
                Role_model role = new Role_model();
                role.setId(UUID.fromString(resultSet.getString("ID")));
                role.setRoleName(resultSet.getString("ROLENAME"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            logger.error("cant getAll",e);
            throw new RoleException("Проблема с базой данных: невозможно получить записи из таблицы ролей!",e);
        }
        return roleList;
    }

    @Override
    public Role_model getOne(UUID id) throws RoleException {
        Role_model role = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ROLESEC_PREP_SELECTONE_QUERY)) {
            statement.setObject(1,id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                role = new Role_model();
                role.setId(UUID.fromString(resultSet.getString("ID")));
                role.setRoleName(resultSet.getString("ROLENAME"));

            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne Role_model!", e);
            throw new RoleException("Проблема с базой данных: невозможно получить запись из таблицы ролей!",e);
        }
        return role;
    }

    @Override
    public boolean create(Role_model entity) throws RoleException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ROLESEC_PREP_INSERT_QUERY);) {
            statement.setObject(1,entity.getId().toString());
            statement.setObject(2,entity.getRoleName());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant create",e);
            throw new RoleException("Проблема с базой данных: невозможно создать запись в таблице ролей!",e);
        }
        return true;
    }

    @Override
    public boolean delete(UUID id) throws RoleException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ROLESEC_PREP_DELETE_QUERY);) {
            statement.setObject(1,id.toString());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant delete",e);
            throw new RoleException("Проблема с базой данных: невозможно удалить запись из таблицы ролей!",e);
        }
        return true;
    }

    @Override
    public boolean update(Role_model entity) throws RoleException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement=connection.prepareStatement(ROLESEC_PREP_UPDATE_QUERY);) {
            statement.setObject(1,entity.getRoleName());
            statement.setObject(2,entity.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("cant update",e);
            throw new RoleException("Проблема с базой данных: невозможно изменить запись в таблице ролей!",e);
        }
        return true;
    }

    @Override
    public Role_model roleByRoleName(String roleName) throws RoleException {
        Role_model role = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ROLESEC_PREP_SELECTONE_BY_NAME_QUERY)) {
            statement.setObject(1,roleName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                role = new Role_model();
                role.setId(UUID.fromString(resultSet.getString("ID")));
                role.setRoleName(resultSet.getString("ROLENAME"));

            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne Role_model!", e);
            throw new RoleException("Проблема с базой данных: невозможно получить запись из таблицы ролей!",e);
        }
        return role;
    }

    @Override
    public List<Role_model> getAllByUserId(UUID id) throws RoleException {
        List<Role_model> roleList = new ArrayList<>();
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ROLESEC_PREP_SELECTALL_BY_USERID_QUERY)) {
            statement.setObject(1,id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Role_model role = new Role_model();
                role.setId(UUID.fromString(resultSet.getString("ID")));
                role.setRoleName(resultSet.getString("ROLENAME"));
                roleList.add(role);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getAllByUserId(UUID id) Role_model!", e);
            throw new RoleException("Проблема с базой данных: невозможно получить запись из таблицы ролей!",e);
        }
        return roleList;
    }
}
*/
