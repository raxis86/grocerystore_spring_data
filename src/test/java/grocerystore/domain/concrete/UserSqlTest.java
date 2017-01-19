/*
package grocerystore.domain.concrete;

import grocerystore.domain.concrete.onlyjdbc.UserSql;
import grocerystore.domain.models.User;
import grocerystore.domain.models.Role;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

*/
/**
 * Created by raxis on 03.01.2017.
 *//*

public class UserSqlTest {

    private EmbeddedDatabase db;
    private UserSql userHandler;
    private DataSource ds;
    private User_model user;
    private List<User_model> userList;

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
        userHandler = new UserSql();
        userHandler.setDs(ds);
    }

    private boolean queryAndGroceryEquals(String query, User_model user){
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);) {
            while (resultSet.next()){
                if((user.getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(user.getEmail().equals(resultSet.getString("EMAIL")))
                        &&(user.getPassword().equals(resultSet.getString("PASSWORD")))
                        &&(user.getStatus().toString().equals(resultSet.getString("STATUS")))
                        &&(user.getName().equals(resultSet.getString("NAME")))
                        &&(user.getLastname().equals(resultSet.getString("LASTNAME")))
                        &&(user.getSurname().equals(resultSet.getString("SURNAME")))
                        &&(user.getAddress().equals(resultSet.getString("ADDRESS")))
                        &&(user.getPhone().equals(resultSet.getString("PHONE")))
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
        userList = userHandler.getAll();

        int i=0;
        boolean flag=false;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS_SEC");) {
            while (resultSet.next()){
                if((userList.get(i).getId().equals(UUID.fromString(resultSet.getString("ID"))))
                        &&(userList.get(i).getEmail().equals(resultSet.getString("EMAIL")))
                        &&(userList.get(i).getPassword().equals(resultSet.getString("PASSWORD")))
                        &&(userList.get(i).getStatus().toString().equals(resultSet.getString("STATUS")))
                        &&(userList.get(i).getName().equals(resultSet.getString("NAME")))
                        &&(userList.get(i).getLastname().equals(resultSet.getString("LASTNAME")))
                        &&(userList.get(i).getSurname().equals(resultSet.getString("SURNAME")))
                        &&(userList.get(i).getAddress().equals(resultSet.getString("ADDRESS")))
                        &&(userList.get(i).getPhone().equals(resultSet.getString("PHONE")))
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
        user = userHandler.getOne(UUID.fromString("839356a3-9a4a-4764-a01e-859ba979ab25"));
        assertTrue(queryAndGroceryEquals("SELECT * FROM USERS_SEC WHERE ID='839356a3-9a4a-4764-a01e-859ba979ab25'",user));
    }

    */
/**
     * Тестирование метода create на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void create() throws Exception {
        user = new User_model();
        List<Role_model> roles = new ArrayList<>();

        user.setId(UUID.fromString("907b09fe-7e54-4b22-8fb9-4a45a449e54e"));
        user.setEmail("test@test.ru");
        user.setPassword("password");
        user.setStatus(User_model.Status.ACTIVE);
        user.setName("TestNae");
        user.setLastname("LastName");
        user.setSurname("Surname");
        user.setAddress("testAdd");
        user.setPhone("123");
        user.setRoles(roles);

        userHandler.create(user);

        assertTrue(queryAndGroceryEquals("SELECT * FROM USERS_SEC WHERE ID='907b09fe-7e54-4b22-8fb9-4a45a449e54e'",user));
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
            statement.execute("INSERT INTO USERS_SEC VALUES ('452025dd-1f1e-4a4f-9964-78cd53dc3ee3','email','pass','status','name','ln','sn','add','ph');" +
                                   "INSERT INTO usersandroles VALUES ('452025dd-1f1e-4a4f-9964-78cd53dc3ee3','2597d800-eec0-4347-84fd-324d1cb8e0bb')");
        } catch (SQLException e) {
        }

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS_SEC WHERE ID='452025dd-1f1e-4a4f-9964-78cd53dc3ee3'");) {
            if(resultSet.next())i++;
        } catch (SQLException e) {
        }


        userHandler.delete(UUID.fromString("452025dd-1f1e-4a4f-9964-78cd53dc3ee3"));

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS_SEC WHERE ID='452025dd-1f1e-4a4f-9964-78cd53dc3ee3'");) {
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
        user = new User_model();
        List<Role_model> roles = new ArrayList<>();

        user.setId(UUID.fromString("839356a3-9a4a-4764-a01e-859ba979ab25"));
        user.setEmail("test@test.ru");
        user.setPassword("password");
        user.setStatus(User_model.Status.ACTIVE);
        user.setName("TestNae");
        user.setLastname("LastName");
        user.setSurname("Surname");
        user.setAddress("testAdd");
        user.setPhone("123");
        user.setRoles(roles);

        userHandler.update(user);

        assertTrue(queryAndGroceryEquals("SELECT * FROM USERS_SEC WHERE ID='839356a3-9a4a-4764-a01e-859ba979ab25'",user));
    }

    */
/**
     * Тестирование метода getOne(String email, String passwordHash) на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void getOne1() throws Exception {
        user = userHandler.getOne("user@mail.ru","$2a$10$noOo/7kXbsf3VClbMogKE.iq8totYmERfhKntGq6sGKpCR30zOH16");
        assertTrue(queryAndGroceryEquals("SELECT * FROM USERS_SEC WHERE ID='839356a3-9a4a-4764-a01e-859ba979ab25'",user));
    }

    */
/**
     * Тестирование метода getOneByEmail(String email) на соответствие запросу
     * @throws Exception
     *//*

    @Test
    public void getOneByEmail() throws Exception {
        user = userHandler.getOneByEmail("user@mail.ru");
        assertTrue(queryAndGroceryEquals("SELECT * FROM USERS_SEC WHERE ID='839356a3-9a4a-4764-a01e-859ba979ab25'",user));
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}*/
