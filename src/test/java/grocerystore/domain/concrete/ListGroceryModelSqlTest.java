/*
package grocerystore.domain.concrete;

import grocerystore.domain.concrete.onlyjdbc.ListGrocerySql;
import grocerystore.domain.models.ListGrocery;
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

public class ListGroceryModelSqlTest {
    private EmbeddedDatabase db;
    private ListGrocerySql listGroceryHandler;
    private DataSource ds;
    private ListGrocery_model listGrocery;
    private List<ListGrocery_model> listGroceryList;

    */
/**
     * Создание тестовой базы H2 из готовых скриптов
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
        listGroceryHandler = new ListGrocerySql();
        listGroceryHandler.setDs(ds);
    }

    private boolean queryAndGroceryEquals(String query, ListGrocery_model listGrocery){
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);) {
            while (resultSet.next()){
                if((listGrocery.getId().equals(UUID.fromString(resultSet.getString("ID"))))
                 &&(listGrocery.getGroceryId().equals(UUID.fromString(resultSet.getString("GROCERYID"))))
                 &&(listGrocery.getQuantity()==resultSet.getInt("QUANTITY"))
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
        listGroceryList = listGroceryHandler.getAll();

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERYLIST");) {
            while (resultSet.next()){
                if((listGroceryList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(listGroceryList.get(i).getGroceryId().equals(UUID.fromString(resultSet.getString("GROCERYID"))))
                        &&(listGroceryList.get(i).getQuantity()==resultSet.getInt("QUANTITY"))
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
        listGrocery = listGroceryHandler.getOne(UUID.fromString("c44b23be-8e24-4096-911d-623d2794b716"));
        assertTrue(queryAndGroceryEquals("SELECT * FROM GROCERYLIST WHERE ID='c44b23be-8e24-4096-911d-623d2794b716'",listGrocery));
    }

    */
/**
     * Тестирование метода create на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void create() throws Exception {
        listGrocery = new ListGrocery_model();

        listGrocery.setId(UUID.fromString("f47591f5-fa34-485f-8c02-618cb7ee88d5"));
        listGrocery.setGroceryId(UUID.fromString("1150b23a-e004-44b2-aa6f-aec18ae69d41"));
        listGrocery.setQuantity(12);

        listGroceryHandler.create(listGrocery);

        assertTrue(queryAndGroceryEquals("SELECT * FROM GROCERYLIST WHERE ID='f47591f5-fa34-485f-8c02-618cb7ee88d5'",listGrocery));
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
            statement.execute("INSERT INTO GROCERYLIST (ID,GROCERYID,QUANTITY) VALUES ('d5630be1-a3e8-4ad4-81e0-bc70c643accd','1150b23a-e004-44b2-aa6f-aec18ae69d41',10)");
        } catch (SQLException e) {
        }

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERYLIST WHERE ID='d5630be1-a3e8-4ad4-81e0-bc70c643accd'");) {
            if(resultSet.next())i++;
        } catch (SQLException e) {
        }


        listGroceryHandler.delete(UUID.fromString("d5630be1-a3e8-4ad4-81e0-bc70c643accd"));

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERYLIST WHERE ID='d5630be1-a3e8-4ad4-81e0-bc70c643accd'");) {
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
        listGrocery = new ListGrocery_model();

        listGrocery.setId(UUID.fromString("c44b23be-8e24-4096-911d-623d2794b716"));
        listGrocery.setGroceryId(UUID.fromString("1150b23a-e004-44b2-aa6f-aec18ae69d41"));
        listGrocery.setQuantity(105);

        listGroceryHandler.update(listGrocery);

        assertTrue(queryAndGroceryEquals("SELECT * FROM GROCERYLIST WHERE ID='c44b23be-8e24-4096-911d-623d2794b716'",listGrocery));
    }

    */
/**
     * Тестирование метода getListById() на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void getListById() throws Exception {
        listGroceryList = listGroceryHandler.getListById(UUID.fromString("c44b23be-8e24-4096-911d-623d2794b716"));

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERYLIST WHERE ID='c44b23be-8e24-4096-911d-623d2794b716'");) {
            while (resultSet.next()){
                if((listGroceryList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(listGroceryList.get(i).getGroceryId().equals(UUID.fromString(resultSet.getString("GROCERYID"))))
                        &&(listGroceryList.get(i).getQuantity()==resultSet.getInt("QUANTITY"))
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
     * Тестирование метода getListByGroceryId() на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void getListByGroceryId() throws Exception {
        listGroceryList = listGroceryHandler.getListByGroceryId(UUID.fromString("1150b23a-e004-44b2-aa6f-aec18ae69d41"));

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERYLIST WHERE GROCERYID='1150b23a-e004-44b2-aa6f-aec18ae69d41'");) {
            while (resultSet.next()){
                if((listGroceryList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(listGroceryList.get(i).getGroceryId().equals(UUID.fromString(resultSet.getString("GROCERYID"))))
                        &&(listGroceryList.get(i).getQuantity()==resultSet.getInt("QUANTITY"))
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
