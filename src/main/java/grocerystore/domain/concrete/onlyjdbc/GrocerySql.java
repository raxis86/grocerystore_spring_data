/*
package grocerystore.domain.concrete.onlyjdbc;

import grocerystore.domain.abstracts.IRepositoryGrocery;
import grocerystore.domain.models.Grocery;
import grocerystore.domain.exceptions.GroceryException;
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
 * Created by raxis on 27.12.2016.
 * Реализакция DAO для работы с grocery в MySQL
 *//*

*/
/*@Repository*//*

public class GrocerySql extends SQLImplementation implements IRepositoryGrocery {
    private static final Logger logger = LoggerFactory.getLogger(GrocerySql.class);

    @Override
    public List<Grocery_model> getAll() throws GroceryException {
        List<Grocery_model> groceryList = new ArrayList<>();
        try(Connection connection = getDs().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GROCERY_SELECTALL_QUERY);) {
            while (resultSet.next()){
                Grocery_model grocery = new Grocery_model();
                grocery.setId(UUID.fromString(resultSet.getString("ID")));
                grocery.setParentid(UUID.fromString(resultSet.getString("PARENTID")));
                grocery.setIscategory(resultSet.getBoolean("ISCATEGORY"));
                grocery.setName(resultSet.getString("NAME"));
                grocery.setQuantity(resultSet.getInt("QUANTITY"));
                grocery.setPrice(resultSet.getBigDecimal("PRICE"));
                groceryList.add(grocery);
            }
        } catch (SQLException e) {
            logger.error("Cant select List of Grocery_model",e);
            throw new GroceryException("Проблема с базой данных: невозможно получить записи из таблицы продуктов!",e);
        }
        return groceryList;
    }

    @Override
    public Grocery_model getOne(UUID id) throws GroceryException {
        Grocery_model grocery = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(GROCERY_PREP_SELECTONE_QUERY);) {
            statement.setObject(1,id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                grocery = new Grocery_model();
                grocery.setId(UUID.fromString(resultSet.getString("ID")));
                grocery.setParentid(UUID.fromString(resultSet.getString("PARENTID")));
                grocery.setIscategory(resultSet.getBoolean("ISCATEGORY"));
                grocery.setName(resultSet.getString("NAME"));
                grocery.setQuantity(resultSet.getInt("QUANTITY"));
                grocery.setPrice(resultSet.getBigDecimal("PRICE"));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne Grocery_model!", e);
            throw new GroceryException("Проблема с базой данных: невозможно получить запись из таблицы продуктов!",e);
        }
        return grocery;
    }

    @Override
    public boolean create(Grocery_model entity) throws GroceryException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(GROCERY_PREP_INSERT_QUERY);) {
            statement.setObject(1,entity.getId().toString());
            statement.setObject(2,entity.getParentid().toString());
            statement.setObject(3,entity.isIscategory());
            statement.setObject(4,entity.getName());
            statement.setObject(5,entity.getQuantity());
            statement.setObject(6,entity.getPrice());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant create",e);
            throw new GroceryException("Проблема с базой данных: невозможно создать запись в таблице продуктов!",e);
        }
        return true;
    }

    @Override
    public boolean delete(UUID id) throws GroceryException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(GROCERY_PREP_DELETE_QUERY);) {
            statement.setObject(1,id.toString());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant delete",e);
            throw new GroceryException("Проблема с базой данных: невозможно удалить запись из таблицы продуктов!",e);
        }
        return true;
    }

    @Override
    public boolean update(Grocery_model entity) throws GroceryException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement=connection.prepareStatement(GROCERY_PREP_UPDATE_QUERY);) {
            statement.setObject(1,entity.getParentid().toString());
            statement.setObject(2,entity.isIscategory());
            statement.setObject(3,entity.getName());
            statement.setObject(4,entity.getQuantity());
            statement.setObject(5,entity.getPrice());
            statement.setObject(6,entity.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("cant update",e);
            throw new GroceryException("Проблема с базой данных: невозможно изменить запись в таблице продуктов!",e);
        }
        return true;
    }
}
*/
