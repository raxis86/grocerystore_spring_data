package grocerystore.constants;

import java.util.UUID;

/**
 * Created by raxis on 23.12.2016.
 */
public class Constants {
    public static final UUID NULL_UUID=UUID.fromString("00000000-0000-0000-0000-000000000000");
    public static final String GROCERY_SELECTALL_QUERY="SELECT * FROM GROCERIES";
    public static final String GROCERY_PREP_SELECTONE_QUERY="SELECT * FROM GROCERIES WHERE ID=?";
    public static final String GROCERY_PREP_INSERT_QUERY="INSERT INTO groceries values (?,?,?,?,?,?)";
    public static final String GROCERY_PREP_UPDATE_QUERY="UPDATE groceries SET PARENTID=?,ISCATEGORY=?,NAME=?,QUANTITY=?,PRICE=? WHERE ID=?";
    public static final String GROCERY_PREP_DELETE_QUERY="DELETE FROM groceries WHERE ID=?";
    public static final String USERSEC_SELECTALL_QUERY="SELECT * FROM users";
    public static final String USERSEC_PREP_SELECTONE_QUERY="SELECT * FROM users WHERE ID=?";
    public static final String USERSEC_PREP_SELECTONE_BY_AUTH_QUERY="SELECT * FROM users WHERE EMAIL=? AND PASSWORD=?";
    public static final String USERSEC_PREP_SELECTONE_BY_EMAIL_QUERY="SELECT * FROM users WHERE EMAIL=?";
    public static final String USERSEC_PREP_SELECTALL_BY_ROLEID_QUERY="SELECT ID,EMAIL from users,usersandroles where users.ID=usersandroles.USER_ID and ROLE_ID=?";
    public static final String USERSEC_PREP_INSERT_QUERY="INSERT INTO users values (?,?,?,?,?,?,?,?,?)";
    public static final String USERSEC_PREP_UPDATE_QUERY="UPDATE users SET EMAIL=?,PASSWORD=?,STATUS=?,NAME=?,LASTNAME=?,SURNAME=?,ADDRESS=?,PHONE=? WHERE ID=?";
    public static final String USERSEC_PREP_DELETE_QUERY="DELETE FROM users WHERE ID=?;";
    public static final String ROLESEC_SELECTALL_QUERY="SELECT * FROM roles";
    public static final String ROLESEC_PREP_SELECTONE_QUERY="SELECT * FROM roles WHERE ID=?";
    public static final String ROLESEC_PREP_SELECTALL_BY_USERID_QUERY="SELECT ID,ROLENAME from roles,usersandroles where roles.ID=usersandroles.ROLE_ID and USER_ID=?";
    public static final String ROLESEC_PREP_SELECTONE_BY_NAME_QUERY="SELECT * FROM roles WHERE ROLENAME=?";
    public static final String ROLESEC_PREP_INSERT_QUERY="INSERT INTO roles values (?,?)";
    public static final String ROLESEC_PREP_UPDATE_QUERY="UPDATE roles SET ROLENAME=? WHERE ID=?";
    public static final String ROLESEC_PREP_DELETE_QUERY="DELETE FROM roles WHERE ID=?";
    public static final String GROCERYLIST_SELECTALL_QUERY="SELECT * FROM grocerylist";
    public static final String GROCERYLIST_PREP_SELECTONE_QUERY="SELECT * FROM grocerylist WHERE ID=?";
    public static final String GROCERYLIST_PREP_SELECT_BY_GROCERYID_QUERY="SELECT * FROM grocerylist WHERE GROCERYID=?";
    public static final String GROCERYLIST_PREP_INSERT_QUERY="INSERT INTO grocerylist (ID,GROCERYID,QUANTITY) values (?,?,?)";
    public static final String GROCERYLIST_PREP_UPDATE_QUERY="UPDATE grocerylist SET GROCERYID=?,QUANTITY=? WHERE ID=?";
    public static final String GROCERYLIST_PREP_DELETE_QUERY="DELETE FROM grocerylist WHERE ID=?";
    public static final String ORDER_SELECTALL_QUERY="SELECT * FROM orders";
    public static final String ORDER_PREP_SELECTONE_QUERY="SELECT * FROM orders WHERE ID=?";
    public static final String ORDER_PREP_SELECT_BY_USERID_QUERY="SELECT * FROM orders WHERE USERID=?";
    public static final String ORDER_PREP_INSERT_QUERY="INSERT INTO orders values (?,?,?,?,?,?,?)";
    public static final String ORDER_PREP_UPDATE_QUERY="UPDATE orders SET USERID=?,STATUSID=?,PRICE=?,DATETIME=?,GROCERYLISTID=?,ADDRESS=? WHERE ID=?";
    public static final String ORDER_PREP_DELETE_QUERY="DELETE FROM orders WHERE ID=?";
    public static final String ORDERSTATUS_SELECTALL_QUERY="SELECT * FROM orderupdates";
    public static final String ORDERSTATUS_PREP_SELECTONE_QUERY="SELECT * FROM orderupdates WHERE ID=?";
    public static final String ORDERSTATUS_PREP_INSERT_QUERY="INSERT INTO orderupdates values (?,?)";
    public static final String ORDERSTATUS_PREP_UPDATE_QUERY="UPDATE orderupdates SET STATUS=? WHERE ID=?";
    public static final String ORDERSTATUS_PREP_DELETE_QUERY="DELETE FROM orderupdates WHERE ID=?";
    public static final String USERSANDROLES_INSERT_QUERY="INSERT INTO usersandroles VALUES (?,?)";
    public static final String USERSANDROLES_DELETE_BY_USERID_QUERY="DELETE FROM usersandroles WHERE USER_ID=?";
}
