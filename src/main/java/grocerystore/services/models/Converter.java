package grocerystore.services.models;

import grocerystore.domain.entityes.*;
import grocerystore.domain.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raxis on 20.01.2017.
 */
public class Converter {
    private static final Logger logger = LoggerFactory.getLogger(Converter.class);

    public static Grocery_model convert(Grocery grocery) {
        if (grocery != null) {
            Grocery_model grocery_model = new Grocery_model();
            grocery_model.setId(grocery.getId());
            grocery_model.setParentid(grocery.getParentid());
            grocery_model.setName(grocery.getName());
            grocery_model.setPrice(grocery.getPrice());
            grocery_model.setIscategory(grocery.isIscategory());
            grocery_model.setQuantity(grocery.getQuantity());

            return grocery_model;
        } else {
            return null;
        }
    }

    public static Grocery convert(Grocery_model grocery_model){
        if(grocery_model!=null){
            Grocery grocery = new Grocery();
            grocery.setId(grocery_model.getId());
            grocery.setParentid(grocery_model.getParentid());
            grocery.setName(grocery_model.getName());
            grocery.setPrice(grocery_model.getPrice());
            grocery.setIscategory(grocery_model.isIscategory());
            grocery.setQuantity(grocery_model.getQuantity());

            return grocery;
        }
        else {
            return null;
        }
    }

    public static ListGrocery_model convert(ListGrocery listGrocery){
        if(listGrocery!=null){
            ListGrocery_model listGrocery_model = new ListGrocery_model();
            listGrocery_model.setId(listGrocery.getId());
            listGrocery_model.setGroceryId(listGrocery.getGroceryid());
            listGrocery_model.setQuantity(listGrocery.getQuantity());

            return listGrocery_model;
        }
        else {
            return null;
        }
    }

    public static ListGrocery convert(ListGrocery_model listGroceryModel){
        if(listGroceryModel!=null){
            ListGrocery listGrocery = new ListGrocery();
            listGrocery.setId(listGroceryModel.getId());
            listGrocery.setGroceryid(listGroceryModel.getGroceryId());
            listGrocery.setQuantity(listGroceryModel.getQuantity());

            return listGrocery;
        }
        else {
            return null;
        }
    }

    public static Order_model convert(Order order){
        if(order!=null){
            Order_model order_model = new Order_model();
            order_model.setId(order.getId());
            order_model.setGrocerylistid(order.getGrocerylistid());
            order_model.setOrderstatusid(order.getOrderstatusid());
            order_model.setUserid(order.getUserid());
            order_model.setAddress(order.getAddress());
            order_model.setDatetime(order.getDatetime());
            order_model.setPrice(order.getPrice());

            return order_model;
        }
        else {
            return null;
        }
    }

    public static Order convert(Order_model order_model){
        if(order_model!=null){
            Order order = new Order();
            order.setId(order_model.getId());
            order.setGrocerylistid(order_model.getGrocerylistid());
            order.setOrderstatusid(order_model.getOrderstatusid());
            order.setUserid(order_model.getUserid());
            order.setAddress(order_model.getAddress());
            order.setDatetime(order_model.getDatetime());
            order.setPrice(order_model.getPrice());

            return order;
        }
        else {
            return null;
        }
    }

    public static OrderStatus_model convert(OrderStatus orderStatus){
        if(orderStatus!=null){
            OrderStatus_model orderStatus_model = new OrderStatus_model();
            orderStatus_model.setId(orderStatus.getId());
            orderStatus_model.setStatus(orderStatus.getStatus());

            return orderStatus_model;
        }
        else {
            return null;
        }
    }

    public static User_model convert(User user){
        if(user!=null){
            User_model user_model = new User_model();
            user_model.setId(user.getId());
            user_model.setEmail(user.getEmail());
            user_model.setStatus(User_model.Status.valueOf(user.getStatus()));
            user_model.setPassword(user.getPassword());
            user_model.setName(user.getName());
            user_model.setLastname(user.getLastname());
            user_model.setSurname(user.getSurname());
            user_model.setAddress(user.getAddress());
            user_model.setPhone(user.getPhone());
            return user_model;
        }
        else
        {
            return null;
        }
    }

    public static Role_model convert(Role role){
        if(role!=null){
            Role_model role_model = new Role_model();
            role_model.setId(role.getId());
            role_model.setRoleName(role.getRoleName());

            return role_model;
        }
        else
        {
            return null;
        }
    }

    public static List<Role_model> convertRoleList(List<Role> roleList){
        List<Role_model> role_modelList = new ArrayList<>();
        for(Role role:roleList){
            role_modelList.add(convert(role));
        }

        return role_modelList;
    }

    public static List<User_model> convertUserList(List<User> userList){
        List<User_model> user_modelList = new ArrayList<>();
        for(User u:userList){
            user_modelList.add(convert(u));
        }
        return user_modelList;
    }

    public static User convert(User_model user_model, List<Role> roleList) {
        if(user_model!=null && roleList!=null){
            User user = new User();
            user.setId(user_model.getId());
            user.setEmail(user_model.getEmail());
            user.setPassword(user_model.getPassword());
            user.setStatus(user_model.getStatus().toString());
            user.setName(user_model.getName());
            user.setLastname(user_model.getLastname());
            user.setSurname(user_model.getSurname());
            user.setPhone(user_model.getPhone());
            user.setAddress(user_model.getAddress());

            user.setRoles(roleList);
            return user;
        }
        else
        {
            return null;
        }
    }
}
