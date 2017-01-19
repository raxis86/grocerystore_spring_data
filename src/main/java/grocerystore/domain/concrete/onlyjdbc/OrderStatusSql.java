/*
package grocerystore.domain.concrete.onlyjdbc;

import grocerystore.domain.abstracts.IRepositoryOrderStatus;
import grocerystore.domain.models.OrderStatus;
import grocerystore.domain.exceptions.OrderStatusException;
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
 * Реализакция DAO для работы с orderstatus в MySQL
 *//*

@Repository
public class OrderStatusSql extends SQLImplementation implements IRepositoryOrderStatus {
    private static final Logger logger = LoggerFactory.getLogger(OrderStatusSql.class);

    @Override
    public List<OrderStatus_model> getAll() throws OrderStatusException {
        List<OrderStatus_model> orderStatusList = new ArrayList<>();
        try(Connection connection = getDs().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(ORDERSTATUS_SELECTALL_QUERY);) {
            while (resultSet.next()){
                OrderStatus_model orderStatus = new OrderStatus_model();
                orderStatus.setId(UUID.fromString(resultSet.getString("ID")));
                orderStatus.setStatus(resultSet.getString("STATUS"));
                orderStatusList.add(orderStatus);
            }
        } catch (SQLException e) {
            logger.error("cant gelAll",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно получить записи из таблицы статусов!",e);
        }
        return orderStatusList;
    }

    @Override
    public OrderStatus_model getOne(UUID id) throws OrderStatusException {
        OrderStatus_model orderStatus = null;
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ORDERSTATUS_PREP_SELECTONE_QUERY)) {
            statement.setObject(1,id.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                orderStatus = new OrderStatus_model();
                orderStatus.setId(UUID.fromString(resultSet.getString("ID")));
                orderStatus.setStatus(resultSet.getString("STATUS"));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cant getOne OrderStatusSql!", e);
            throw new OrderStatusException("Проблема с базой данных: невозможно получить запись из таблицы статусов!",e);
        }
        return orderStatus;
    }

    @Override
    public boolean create(OrderStatus_model entity) throws OrderStatusException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ORDERSTATUS_PREP_INSERT_QUERY);) {
            statement.setObject(1,entity.getId().toString());
            statement.setObject(2,entity.getStatus());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant create",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно создать запись в таблице статусов!",e);
        }
        return true;
    }

    @Override
    public boolean delete(UUID id) throws OrderStatusException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement(ORDERSTATUS_PREP_DELETE_QUERY);) {
            statement.setObject(1,id.toString());
            statement.execute();
        } catch (SQLException e) {
            logger.error("cant delete",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно удалить запись в таблице статусов!",e);
        }
        return true;
    }

    @Override
    public boolean update(OrderStatus_model entity) throws OrderStatusException {
        try(Connection connection = getDs().getConnection();
            PreparedStatement statement=connection.prepareStatement(ORDERSTATUS_PREP_UPDATE_QUERY);) {
            statement.setObject(1,entity.getStatus());
            statement.setObject(2,entity.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("cant update",e);
            throw new OrderStatusException("Проблема с базой данных: невозможно изменить запись в таблице статусов!",e);
        }
        return true;
    }
}
*/
