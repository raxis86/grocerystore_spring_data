package grocerystore.services.concrete;

import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.entityes.Order;
import grocerystore.domain.entityes.OrderStatus;
import grocerystore.domain.entityes.User;
import grocerystore.domain.models.*;
import grocerystore.domain.repositories.*;
import grocerystore.services.abstracts.IOrderService;
import grocerystore.services.exceptions.OrderServiceException;
import grocerystore.services.models.Cart;
import grocerystore.services.viewmodels.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.*;

import static grocerystore.services.models.Converter.convert;

/**
 * Created by raxis on 29.12.2016.
 */
@Service
public class OrderService implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private OrderRepository orderHandler;
    private OrderStatusRepository orderStatusHandler;
    private ListGroceryRepository listGroceryHandler;
    private GroceryRepository groceryHandler;
    private UserRepository userHandler;

    public OrderService(OrderRepository orderHandler,
                        OrderStatusRepository orderStatusHandler,
                        ListGroceryRepository listGroceryHandler,
                        GroceryRepository groceryHandler,
                        UserRepository userHandler){
        this.orderHandler=orderHandler;
        this.orderStatusHandler=orderStatusHandler;
        this.listGroceryHandler=listGroceryHandler;
        this.groceryHandler=groceryHandler;
        this.userHandler=userHandler;
    }

    @Override
    @Secured("ROLE_USER")
    public Order_model createOrder(User_model userModel, Cart cart) throws OrderServiceException {
        Order_model orderModel = new Order_model();
        orderModel.setId(UUID.randomUUID());
        orderModel.setUserid(userModel.getId());
        orderModel.setOrderstatusid(UUID.fromString("c24be575-187f-4d41-82ee-ff874764b829"));
        orderModel.setPrice(cart.computeTotalPrice());
        orderModel.setDatetime(new Date());
        orderModel.setGrocerylistid(UUID.randomUUID());
        orderModel.setAddress(userModel.getAddress());

        try {
            orderHandler.save(convert(orderModel));
        } catch (Exception e) {
            logger.error("cant createOrder",e);
            throw new OrderServiceException("Невозможно сформировать заказ!",e);
        }

        return orderModel;
    }

    /**
     * формирование формы заказа для отображения
     * @param orderid String from controller
     * @return OrderView
     */
    @Override
    public OrderView formOrderView(String orderid) throws OrderServiceException {
        return formOrderView(UUID.fromString(orderid),"");
    }

    @Override
    public List<OrderView> formOrderViewListAdmin() throws OrderServiceException {
        List<OrderView> orderViewList = new ArrayList<>();
        List<Order> orderList;

        try {
            orderList = (List<Order>)orderHandler.findAll();
        } catch (Exception e) {
            logger.error("cant getAll",e);
            throw new OrderServiceException("Невозможно сформировать список заказов!",e);
        }

        if(orderList.size()!=0){
            for(Order o: orderList){
                try {
                    UUID userid = o.getUserid();
                    if(userid!=null){
                        User user = userHandler.findOne(userid);
                        if(user !=null){
                            orderViewList.add(formOrderView(o.getId(), user.getEmail()));
                        }
                    }
                } catch (Exception e) {
                    logger.error("cant user.getOne",e);
                    throw new OrderServiceException("Невозможно сформировать список заказов!",e);
                }
            }
        }

        return orderViewList;
    }

    @Override
    public List<OrderView> formOrderViewList(User_model userModel) throws OrderServiceException {
        List<OrderView> orderViewList = new ArrayList<>();
        List<Order> orderList;

        try {
            orderList = (List<Order>) orderHandler.findAllByUserid(userModel.getId());
        } catch (Exception e) {
            logger.error("cant order.getByUserId",e);
            throw new OrderServiceException("Невозможно сформировать список заказов!",e);
        }

        for(Order o : orderList){
            if(!o.getOrderstatusid().toString().equals("1c8d12cf-6b0a-4168-ae2a-cb416cf30da5")){
                orderViewList.add(formOrderView(o.getId(),
                                  String.format("%s %s %s", userModel.getLastname(),
                                                            userModel.getName(),
                                                            userModel.getSurname())));
            }
        }

        return orderViewList;
    }

    @Override
    public void updateOrder(String orderid) throws OrderServiceException {
        Order order;

        try {
            order = orderHandler.findOne(UUID.fromString(orderid));
        } catch (Exception e) {
            logger.error("cant orderModel.getOne",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }

        order.setOrderstatusid(UUID.fromString("1c8d12cf-6b0a-4168-ae2a-cb416cf30da5"));

        try {
            orderHandler.save(order);
        } catch (Exception e) {
            logger.error("cant update orderModel",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }
    }

    @Override
    public void updateOrderAdmin(String orderid, String statusid) throws OrderServiceException {
        Order order;

        try {
            order = orderHandler.findOne(UUID.fromString(orderid));
        } catch (Exception e) {
            logger.error("cant orderModel.getOne",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }

        order.setOrderstatusid(UUID.fromString(statusid));

        try {
            orderHandler.save(order);
        } catch (Exception e) {
            logger.error("cant update orderModel",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }
    }

    @Override
    public Order_model getOrder(String orderid) throws OrderServiceException {
        Order_model orderModel;

        try {
            orderModel = convert(orderHandler.findOne(UUID.fromString(orderid)));
        } catch (Exception e) {
            logger.error("cant orderModel.getOne",e);
            throw new OrderServiceException("Невозможно найти заказ!",e);
        }

        return orderModel;
    }

    private OrderView formOrderView(UUID orderid, String userName) throws OrderServiceException {
        Map<String,Integer> map = new HashMap<>();
        Map<String,String> statusMap = new HashMap<>();
        Order order;
        List<ListGrocery> listGroceries;
        OrderStatus orderStatus;
        List<OrderStatus> orderStatusList;

        try {
            order = orderHandler.findOne(orderid);
            listGroceries = (List<ListGrocery>)listGroceryHandler.findAllById(order.getGrocerylistid());
            orderStatus = orderStatusHandler.findOne(order.getOrderstatusid());
            orderStatusList = (List<OrderStatus>)orderStatusHandler.findAll();
        } catch (Exception e) {
            logger.error("cant ListGrocery_model.getListById",e);
            throw new OrderServiceException("Невозможно сформировать заказ!",e);
        }


        OrderView orderView = new OrderView();

        orderView.setId(order.getId().toString());
        orderView.setAddress(order.getAddress());
        orderView.setStatus(orderStatus.getStatus());
        orderView.setDate(order.getDatetime().toString());
        orderView.setFullName(userName);
        orderView.setPrice(order.getPrice().toString());

        for(ListGrocery list: listGroceries){
            String str;
            try {
                str = groceryHandler.findOne(list.getGroceryid()).getName();
            } catch (Exception e) {
                logger.error("cant grocery.getOne",e);
                throw new OrderServiceException("Невозможно сформировать заказ!",e);
            }
            map.put(str,list.getQuantity());
        }
        orderView.setGroceries(map);

        for(OrderStatus os : orderStatusList){
            statusMap.put(os.getId().toString(),os.getStatus());
        }

        orderView.setStatuses(statusMap);

        return orderView;
    }
}
