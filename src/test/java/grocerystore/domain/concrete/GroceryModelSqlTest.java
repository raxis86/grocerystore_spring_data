/*
package grocerystore.domain.concrete;

import grocerystore.domain.concrete.onlyjdbc.GrocerySql;
import grocerystore.domain.models.Grocery_model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

*/
/**
 * Created by raxis on 02.01.2017.
 *//*

public class GroceryModelSqlTest {

    private EmbeddedDatabase db;
    private GrocerySql groceryHandler;
    private DataSource ds;
    private Grocery_model grocery;
    private List<Grocery_model> groceryList;

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
        groceryHandler = new GrocerySql();
        groceryHandler.setDs(ds);
    }

    private boolean queryAndGroceryEquals(String query, Grocery_model grocery){
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);) {
            while (resultSet.next()){
                if((grocery.getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(grocery.getParentid().equals(UUID.fromString(resultSet.getString("PARENTID"))))
                        &&(grocery.isIscategory()==resultSet.getBoolean("ISCATEGORY"))
                        &&(grocery.getName().equals(resultSet.getString("NAME")))
                        &&(grocery.getQuantity()==resultSet.getInt("QUANTITY"))
                        &&(grocery.getPrice().equals(resultSet.getBigDecimal("PRICE")))
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
        groceryList = groceryHandler.getAll();

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERIES");) {
            while (resultSet.next()){
                if((groceryList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID")))
                 &&(groceryList.get(i).getParentid().equals(UUID.fromString(resultSet.getString("PARENTID")))
                 &&(groceryList.get(i).isIscategory()==resultSet.getBoolean("ISCATEGORY")))
                 &&(groceryList.get(i).getName().equals(resultSet.getString("NAME")))
                 &&(groceryList.get(i).getQuantity()==resultSet.getInt("QUANTITY")))
                 &&(groceryList.get(i).getPrice().equals(resultSet.getBigDecimal("PRICE")))
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
        grocery = groceryHandler.getOne(UUID.fromString("1150b23a-e004-44b2-aa6f-aec18ae69d41"));
        assertTrue(queryAndGroceryEquals("SELECT * FROM GROCERIES WHERE ID='1150b23a-e004-44b2-aa6f-aec18ae69d41'",grocery));
    }

    */
/**
     * Тестирование метода create на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void create() throws Exception {
        grocery = new Grocery_model();

        grocery.setId(UUID.fromString("50584b25-c86b-46bf-8a4f-b6b130b42ad1"));
        grocery.setParentid(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        grocery.setQuantity(12);
        grocery.setName("Test");
        grocery.setIscategory(false);
        grocery.setPrice(BigDecimal.valueOf(10.55));

        groceryHandler.create(grocery);

        assertTrue(queryAndGroceryEquals("SELECT * FROM GROCERIES WHERE ID='50584b25-c86b-46bf-8a4f-b6b130b42ad1'",grocery));
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
            statement.execute("INSERT INTO GROCERIES VALUES ('23f2cbb2-1111-4000-8359-ee84091c0e7a','00000000-0000-0000-0000-000000000000',0,'appleTest',100,100.55)");
        } catch (SQLException e) {
        }

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERIES WHERE ID='23f2cbb2-1111-4000-8359-ee84091c0e7a'");) {
            if(resultSet.next())i++;
        } catch (SQLException e) {
        }


        groceryHandler.delete(UUID.fromString("23f2cbb2-1111-4000-8359-ee84091c0e7a"));

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GROCERIES WHERE ID='23f2cbb2-1111-4000-8359-ee84091c0e7a'");) {
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
        grocery = new Grocery_model();

        grocery.setId(UUID.fromString("1150b23a-e004-44b2-aa6f-aec18ae69d41"));
        grocery.setParentid(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        grocery.setQuantity(100555);
        grocery.setName("cherryUpd");
        grocery.setIscategory(false);
        grocery.setPrice(BigDecimal.valueOf(222.65));

        groceryHandler.update(grocery);

        assertTrue(queryAndGroceryEquals("SELECT * FROM GROCERIES WHERE ID='1150b23a-e004-44b2-aa6f-aec18ae69d41'",grocery));
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}*/
