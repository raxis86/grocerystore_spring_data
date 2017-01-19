/*
package grocerystore.domain.concrete.onlyjdbc;

import grocerystore.domain.abstracts.IRepositoryOrder;
import grocerystore.domain.models.Order;
import grocerystore.domain.exceptions.OrderException;
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
 * Реализакция DAO для работы с order в MySQL
 *//*

@Repository
public class OrderSql extends SQLImplementation implements IRepositoryOrder {
    private static final Logger logger = LoggerFactory.getLogger(OrderSql.class);

    private void fillOrder(Order_model order, ResultSet resultSet) throws SQLException {
        order.setId(UUID.fromString(resultSet.getString("ID")));
        order.setUserid(UUID.fromString(resultSet.getString("USERID")));
        order.setOrderstatusid(UUID.fromString(resultSet.getString("STATUSID")));
        order.setGrocerylistid(UUID.fromString(resultSet.getString("GROCERYLISTID")));
        order.setPrice(resultSet.getBigDecimal("PRICE"));
        order.setDatetime(resultSet.getDate("DATETIME"));
        order.setAddress(resultSet.getString("ADDRESS"));
    }

    @Override
    public List<Order_model> getAll() throws OrderException {
        List<Order_model> orderList = new ArrayList<>();
        try(Connection connection = getDs().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(ORDER_SELECTALL_QUERY);) {
            while (resultSet.next()){
                Order_model order = new Order_model();
                fillOrder(order,resultSet);
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.error("cant getAll",e);
            throw new OrderException("Проблема с базой данных: невозможно получить записи из таблицы заказов!",e);
        }
        return orderList;
    }

    @Override
    public Order_model getOne(UUID id) throws OrderException {
        Order_model order = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ORDER_PREP_SELECTONE_QUERY)) {
            statement.setObject(1,id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                order = new Order_model();
                fillOrder(order,resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne Order_model!", e);
            throw new OrderException("Проблема с базой данных: невозможно получить запись из таблицы заказов!",e);
        }
        return order;
    }

    @Override
    public boolean create(Order_model entity) throws OrderException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ORDER_PREP_INSERT_QUERY);) {
            statement.setObject(1,entity.getId().toString());
            statement.setObject(2,entity.getUserid().toString());
            statement.setObject(3,entity.getOrderstatusid().toString());
            statement.setObject(4,entity.getPrice());
            statement.setObject(5,entity.getDatetime());
            statement.setObject(6,entity.getGrocerylistid().toString());
            statement.setObject(7,entity.getAddress());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant create",e);
            throw new OrderException("Проблема с базой данных: невозможно создать запись в таблице заказов!",e);
        }
        return true;
    }

    @Override
    public boolean delete(UUID id) throws OrderException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ORDER_PREP_DELETE_QUERY);) {
            statement.setObject(1,id.toString());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant delete",e);
            throw new OrderException("Проблема с базой данных: невозможно удалить запись из таблицы заказов!",e);
        }
        return true;
    }

    @Override
    public boolean update(Order_model entity) throws OrderException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement=connection.prepareStatement(ORDER_PREP_UPDATE_QUERY);) {
            statement.setObject(1,entity.getUserid().toString());
            statement.setObject(2,entity.getOrderstatusid().toString());
            statement.setObject(3,entity.getPrice());
            statement.setObject(4,entity.getDatetime());
            statement.setObject(5,entity.getGrocerylistid().toString());
            statement.setObject(6,entity.getAddress());
            statement.setObject(7,entity.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("cant update",e);
            throw new OrderException("Проблема с базой данных: невозможно изменить запись в таблице заказов!",e);
        }
        return true;
    }

    @Override
    public List<Order_model> getByUserId(UUID userid) throws OrderException {
        List<Order_model> orderList = new ArrayList<>();
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ORDER_PREP_SELECT_BY_USERID_QUERY);) {
            statement.setObject(1,userid.toString());
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Order_model order = new Order_model();
                fillOrder(order,resultSet);
                orderList.add(order);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("cant getByUserId",e);
            throw new OrderException("Проблема с базой данных: невозможно получить записи из таблицы заказов!",e);
        }
        return orderList;
    }
}
*/
