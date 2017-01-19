package grocerystore.services.concrete;

import grocerystore.domain.abstracts.*;
import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.entityes.Order;
import grocerystore.domain.entityes.OrderStatus;
import grocerystore.domain.entityes.User;
import grocerystore.domain.models.*;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.ListGroceryException;
import grocerystore.domain.exceptions.OrderException;
import grocerystore.services.abstracts.IOrderService;
import grocerystore.services.exceptions.OrderServiceException;
import grocerystore.services.models.Cart;
import grocerystore.services.viewmodels.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by raxis on 29.12.2016.
 */
@Service
public class OrderService implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private IRepositoryOrder orderHandler;
    private IRepositoryOrderStatus orderStatusHandler;
    private IRepositoryListGrocery listGroceryHandler;
    private IRepositoryGrocery groceryHandler;
    private IRepositoryUser userHandler;

    public OrderService(IRepositoryOrder orderHandler,
                        IRepositoryOrderStatus orderStatusHandler,
                        IRepositoryListGrocery listGroceryHandler,
                        IRepositoryGrocery groceryHandler,
                        IRepositoryUser userHandler){
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
            orderHandler.create(convert(orderModel));
        } catch (DAOException e) {
            logger.error("cant createOrder",e);
            throw new OrderServiceException("Невозможно сформировать заказ!",e);
        }

        return orderModel;
    }

    /**
     * формирование формы заказа для отображения
     * @param orderid
     * @return
     */
    @Override
    public OrderView formOrderView(String orderid) throws OrderServiceException {
        return formOrderView(UUID.fromString(orderid),"");
    }

    @Override
    public List<OrderView> formOrderViewListAdmin() throws OrderServiceException {
        List<OrderView> orderViewList = new ArrayList<>();
        List<Order_model> orderModelList =new ArrayList<>();

        try {
            for(Order o:orderHandler.getAll()){
                orderModelList.add(convert(o));
            }
            //orderModelList =orderHandler.getAll();
        } catch (DAOException e) {
            logger.error("cant getAll",e);
            throw new OrderServiceException("Невозможно сформировать список заказов!",e);
        }

        if(orderModelList !=null){
            for(Order_model repoOrderModel : orderModelList){
                try {
                    UUID userid = repoOrderModel.getUserid();
                    if(userid!=null){
                        User user = userHandler.getOne(userid);
                        if(user !=null){
                            orderViewList.add(formOrderView(repoOrderModel.getId(), user.getEmail()));
                        }
                    }
                } catch (DAOException e) {
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
        List<Order_model> orderModelList =new ArrayList<>();

        try {
            for(Order o:orderHandler.getByUserId(userModel.getId())){
                orderModelList.add(convert(o));
            }
            //orderModelList =orderHandler.getByUserId(userModel.getId());
        } catch (OrderException e) {
            logger.error("cant order.getByUserId",e);
            throw new OrderServiceException("Невозможно сформировать список заказов!",e);
        }

        for(Order_model repoOrderModel : orderModelList){
            if(!repoOrderModel.getOrderstatusid().toString().equals("1c8d12cf-6b0a-4168-ae2a-cb416cf30da5")){
                orderViewList.add(formOrderView(repoOrderModel.getId(),String.format("%s %s %s", userModel.getLastname(), userModel.getName(), userModel.getSurname())));
            }
        }

        return orderViewList;
    }

    @Override
    public void updateOrder(String orderid) throws OrderServiceException {
        Order_model orderModel = null;

        try {
            orderModel =convert(orderHandler.getOne(UUID.fromString(orderid)));
        } catch (DAOException e) {
            logger.error("cant orderModel.getOne",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }

        orderModel.setOrderstatusid(UUID.fromString("1c8d12cf-6b0a-4168-ae2a-cb416cf30da5"));

        try {
            orderHandler.update(convert(orderModel));
        } catch (DAOException e) {
            logger.error("cant update orderModel",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }
    }

    @Override
    public void updateOrderAdmin(String orderid, String statusid) throws OrderServiceException {
        Order_model orderModel = null;

        try {
            orderModel =convert(orderHandler.getOne(UUID.fromString(orderid)));
        } catch (DAOException e) {
            logger.error("cant orderModel.getOne",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }

        orderModel.setOrderstatusid(UUID.fromString(statusid));

        try {
            orderHandler.update(convert(orderModel));
        } catch (DAOException e) {
            logger.error("cant update orderModel",e);
            throw new OrderServiceException("Невозможно сохранить изменения!",e);
        }
    }

    @Override
    public Order_model getOrder(String orderid) throws OrderServiceException {
        Order_model orderModel =null;

        try {
            orderModel = convert(orderHandler.getOne(UUID.fromString(orderid)));
        } catch (DAOException e) {
            logger.error("cant orderModel.getOne",e);
            throw new OrderServiceException("Невозможно найти заказ!",e);
        }

        return orderModel;
    }

    private OrderView formOrderView(UUID orderid, String userName) throws OrderServiceException {
        Map<String,Integer> map = new HashMap<>();
        Map<String,String> statusMap = new HashMap<>();
        Order_model orderModel;
        List<ListGrocery_model> listGroceries = new ArrayList<>();
        OrderStatus_model orderStatusModel;
        List<OrderStatus_model> orderStatusModelList = new ArrayList<>();

        try {
            orderModel = convert(orderHandler.getOne(orderid));
            for(ListGrocery lg:listGroceryHandler.getListById(orderModel.getGrocerylistid())){
                listGroceries.add(convert(lg));
            }
            /*listGroceries = listGroceryHandler.getListById(orderModel.getGrocerylistid());*/
            orderStatusModel = convert(orderStatusHandler.getOne(orderModel.getOrderstatusid()));
            for(OrderStatus os:orderStatusHandler.getAll()) {
                orderStatusModelList.add(convert(os));
            }
            //orderStatusModelList = orderStatusHandler.getAll();
        } catch (ListGroceryException e) {
            logger.error("cant ListGrocery_model.getListById",e);
            throw new OrderServiceException("Невозможно сформировать заказ!",e);
        } catch (DAOException e) {
            logger.error("cant orderModel get",e);
            throw new OrderServiceException("Невозможно сформировать заказ!",e);
        }


        OrderView orderView = new OrderView();

        orderView.setId(orderModel.getId().toString());
        orderView.setAddress(orderModel.getAddress());
        orderView.setStatus(orderStatusModel.getStatus());
        orderView.setDate(orderModel.getDatetime().toString());
        orderView.setFullName(userName);
        orderView.setPrice(orderModel.getPrice().toString());

        for(ListGrocery_model list: listGroceries){
            String str;
            try {
                str = groceryHandler.getOne(list.getGroceryId()).getName();
            } catch (DAOException e) {
                logger.error("cant grocery.getOne",e);
                throw new OrderServiceException("Невозможно сформировать заказ!",e);
            }
            map.put(str,list.getQuantity());
        }
        orderView.setGroceries(map);

        for(OrderStatus_model os : orderStatusModelList){
            statusMap.put(os.getId().toString(),os.getStatus());
        }

        orderView.setStatuses(statusMap);

        return orderView;
    }

    private ListGrocery_model convert(ListGrocery listGrocery){
        ListGrocery_model listGrocery_model = new ListGrocery_model();
        listGrocery_model.setId(listGrocery.getId());
        listGrocery_model.setGroceryId(listGrocery.getGroceryId());
        listGrocery_model.setQuantity(listGrocery.getQuantity());

        return listGrocery_model;
    }

    private Order_model convert(Order order){
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

    private Order convert(Order_model order_model){
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

    private OrderStatus_model convert(OrderStatus orderStatus){
        OrderStatus_model orderStatus_model = new OrderStatus_model();
        orderStatus_model.setId(orderStatus.getId());
        orderStatus_model.setStatus(orderStatus.getStatus());

        return orderStatus_model;
    }
}
