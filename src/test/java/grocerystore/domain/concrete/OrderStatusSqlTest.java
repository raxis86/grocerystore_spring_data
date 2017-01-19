/*
package grocerystore.domain.concrete;

import grocerystore.domain.concrete.onlyjdbc.OrderStatusSql;
import grocerystore.domain.models.OrderStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

*/
/**
 * Created by raxis on 03.01.2017.
 *//*

public class OrderStatusSqlTest {

    private EmbeddedDatabase db;
    private OrderStatusSql orderStatusHandler;
    private DataSource ds;
    private OrderStatus_model orderStatus;
    private List<OrderStatus_model> orderStatusList;

    */
/**
     * Подготавливаем тестовую БД H2 на основе скриптов
     * @throws Exception
     *//*

    @Before
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/create_db.sql")
                .addScript("db/insert_db.sql")
                .build();

        ds = (DataSource)db;
        orderStatusHandler = new OrderStatusSql();
        orderStatusHandler.setDs(ds);
    }

    private boolean queryAndGroceryEquals(String query, OrderStatus_model orderStatus){
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);) {
            while (resultSet.next()){
                if((orderStatus.getId().equals(UUID.fromString(resultSet.getString("ID"))))
                 &&(orderStatus.getStatus().equals(resultSet.getString("STATUS")))
                 ){
                    flag=true;
                }
            }
        } catch (SQLException e) {
        }

        return flag;
    }

    */
/**
     * Тестирование метода getAll на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void getAll() throws Exception {
        orderStatusList = orderStatusHandler.getAll();

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERUPDATES");) {
            while (resultSet.next()){
                if((orderStatusList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(orderStatusList.get(i).getStatus().equals(resultSet.getString("STATUS")))
                        ){
                    flag=true;
                }
                i++;
            }
        } catch (SQLException e) {
        }

        assertTrue(flag);
    }

    */
/**
     * Тестирование метода getOne на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void getOne() throws Exception {
        orderStatus = orderStatusHandler.getOne(UUID.fromString("1c8d12cf-6b0a-4168-ae2a-cb416cf30da5"));
        assertTrue(queryAndGroceryEquals("SELECT * FROM ORDERUPDATES WHERE ID='1c8d12cf-6b0a-4168-ae2a-cb416cf30da5'",orderStatus));
    }

    */
/**
     * Тестирование метода create на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void create() throws Exception {
        orderStatus = new OrderStatus_model();

        orderStatus.setId(UUID.fromString("f580a28a-24ff-4b47-8756-e7f14e15f711"));
        orderStatus.setStatus("Test");

        orderStatusHandler.create(orderStatus);

        assertTrue(queryAndGroceryEquals("SELECT * FROM ORDERUPDATES WHERE ID='f580a28a-24ff-4b47-8756-e7f14e15f711'",orderStatus));
    }

    */
/**
     * Тестирование метода delete на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void delete() throws Exception {
        int i=0;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO ORDERUPDATES VALUES ('620b6927-9b7a-4698-8a83-a2b68c4e3208','appTest')");
        } catch (SQLException e) {
        }

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERUPDATES WHERE ID='620b6927-9b7a-4698-8a83-a2b68c4e3208'");) {
            if(resultSet.next())i++;
        } catch (SQLException e) {
        }


        orderStatusHandler.delete(UUID.fromString("620b6927-9b7a-4698-8a83-a2b68c4e3208"));

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERUPDATES WHERE ID='620b6927-9b7a-4698-8a83-a2b68c4e3208'");) {
            if(resultSet.next())i--;
        } catch (SQLException e) {
        }

        assertTrue(i==1);
    }

    */
/**
     * Тестирование метода update на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void update() throws Exception {
        orderStatus = new OrderStatus_model();

        orderStatus.setId(UUID.fromString("b1ed9007-e220-4a2b-8a81-eee2ebc8e277"));
        orderStatus.setStatus("Test+");

        orderStatusHandler.update(orderStatus);

        assertTrue(queryAndGroceryEquals("SELECT * FROM ORDERUPDATES WHERE ID='b1ed9007-e220-4a2b-8a81-eee2ebc8e277'",orderStatus));
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}*/
