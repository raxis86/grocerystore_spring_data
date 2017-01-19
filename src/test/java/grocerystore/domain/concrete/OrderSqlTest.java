/*
package grocerystore.domain.concrete;

import grocerystore.domain.concrete.onlyjdbc.OrderSql;
import grocerystore.domain.models.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

*/
/**
 * Created by raxis on 03.01.2017.
 *//*

public class OrderSqlTest {

    private EmbeddedDatabase db;
    private OrderSql orderHandler;
    private DataSource ds;
    private Order_model order;
    private List<Order_model> orderList;

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
        orderHandler = new OrderSql();
        orderHandler.setDs(ds);
    }

    private boolean queryAndGroceryEquals(String query, Order_model order){
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);) {
            while (resultSet.next()){
                if((order.getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(order.getUserid().equals(UUID.fromString(resultSet.getString("USERID"))))
                        &&(order.getOrderstatusid().equals(UUID.fromString(resultSet.getString("STATUSID"))))
                        &&(order.getGrocerylistid().equals(UUID.fromString(resultSet.getString("GROCERYLISTID"))))
                        &&(order.getDatetime().equals(resultSet.getTimestamp("DATETIME")))
                        &&(order.getPrice().equals(resultSet.getBigDecimal("PRICE")))
                        &&(order.getAddress().equals(resultSet.getString("ADDRESS")))
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
        orderList = orderHandler.getAll();

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS");) {
            while (resultSet.next()){
                if((orderList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(orderList.get(i).getUserid().equals(UUID.fromString(resultSet.getString("USERID"))))
                        &&(orderList.get(i).getOrderstatusid().equals(UUID.fromString(resultSet.getString("STATUSID"))))
                        &&(orderList.get(i).getGrocerylistid().equals(UUID.fromString(resultSet.getString("GROCERYLISTID"))))
                        &&(orderList.get(i).getDatetime().equals(resultSet.getDate("DATETIME")))
                        &&(orderList.get(i).getPrice().equals(resultSet.getBigDecimal("PRICE")))
                        &&(orderList.get(i).getAddress().equals(resultSet.getString("ADDRESS")))
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
        order = orderHandler.getOne(UUID.fromString("0101b824-da41-481d-a0c4-76a4dbabbc3c"));
        assertTrue(queryAndGroceryEquals("SELECT * FROM ORDERS WHERE ID='0101b824-da41-481d-a0c4-76a4dbabbc3c'",order));
    }

    */
/**
     * Тестирование метода create на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void create() throws Exception {
        order = new Order_model();

        order.setId(UUID.fromString("b8bcb4ca-17fe-4a39-b971-3f85a9f6591f"));
        order.setUserid(UUID.fromString("839356a3-9a4a-4764-a01e-859ba979ab25"));
        order.setOrderstatusid(UUID.fromString("c24be575-187f-4d41-82ee-ff874764b829"));
        order.setPrice(BigDecimal.valueOf(111.55));
        order.setDatetime(new Date());
        order.setGrocerylistid(UUID.fromString("c44b23be-8e24-4096-911d-623d2794b716"));
        order.setAddress("Tatarstan");

        orderHandler.create(order);

        assertTrue(queryAndGroceryEquals("SELECT * FROM ORDERS WHERE ID='b8bcb4ca-17fe-4a39-b971-3f85a9f6591f'",order));
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
            statement.execute("INSERT INTO ORDERS VALUES ('a2df671e-bdb9-4774-a74e-7aeae8bec1a1','839356a3-9a4a-4764-a01e-859ba979ab25','c24be575-187f-4d41-82ee-ff874764b829',555,'2016-12-3','c44b23be-8e24-4096-911d-623d2794b716','E-E')");
        } catch (SQLException e) {
        }

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE ID='a2df671e-bdb9-4774-a74e-7aeae8bec1a1'");) {
            if(resultSet.next())i++;
        } catch (SQLException e) {
        }


        orderHandler.delete(UUID.fromString("a2df671e-bdb9-4774-a74e-7aeae8bec1a1"));

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE ID='a2df671e-bdb9-4774-a74e-7aeae8bec1a1'");) {
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
        order = new Order_model();

        order.setId(UUID.fromString("0101b824-da41-481d-a0c4-76a4dbabbc3c"));
        order.setUserid(UUID.fromString("839356a3-9a4a-4764-a01e-859ba979ab25"));
        order.setOrderstatusid(UUID.fromString("c24be575-187f-4d41-82ee-ff874764b829"));
        order.setPrice(BigDecimal.valueOf(111.55));
        order.setDatetime(new Date());
        order.setGrocerylistid(UUID.fromString("c44b23be-8e24-4096-911d-623d2794b716"));
        order.setAddress("Tatarstan");

        orderHandler.update(order);

        assertTrue(queryAndGroceryEquals("SELECT * FROM ORDERS WHERE ID='0101b824-da41-481d-a0c4-76a4dbabbc3c'",order));
    }

    */
/**
     * Тестирование метода getByUserId() на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void getByUserId() throws Exception {
        orderList = orderHandler.getByUserId(UUID.fromString("839356a3-9a4a-4764-a01e-859ba979ab25"));

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE USERID='839356a3-9a4a-4764-a01e-859ba979ab25'");) {
            while (resultSet.next()){
                if((orderList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(orderList.get(i).getUserid().equals(UUID.fromString(resultSet.getString("USERID"))))
                        &&(orderList.get(i).getOrderstatusid().equals(UUID.fromString(resultSet.getString("STATUSID"))))
                        &&(orderList.get(i).getGrocerylistid().equals(UUID.fromString(resultSet.getString("GROCERYLISTID"))))
                        &&(orderList.get(i).getDatetime().equals(resultSet.getDate("DATETIME")))
                        &&(orderList.get(i).getPrice().equals(resultSet.getBigDecimal("PRICE")))
                        &&(orderList.get(i).getAddress().equals(resultSet.getString("ADDRESS")))
                        ){
                    flag=true;
                }
                i++;
            }
        } catch (SQLException e) {
        }

        assertTrue(flag);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}*/
