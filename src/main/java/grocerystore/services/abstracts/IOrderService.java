package grocerystore.services.abstracts;

import grocerystore.domain.models.Order_model;
import grocerystore.domain.models.User_model;
import grocerystore.services.exceptions.OrderServiceException;
import grocerystore.services.models.Cart;
import grocerystore.services.viewmodels.OrderView;

import java.util.List;

/**
 * Created by raxis on 29.12.2016.
 */
public interface IOrderService {
    public Order_model createOrder(User_model userModel, Cart cart) throws OrderServiceException;
    public OrderView formOrderView(String orderid) throws OrderServiceException;
    public List<OrderView> formOrderViewListAdmin() throws OrderServiceException;
    public List<OrderView> formOrderViewList(User_model userModel) throws OrderServiceException;
    public void updateOrder(String orderid) throws OrderServiceException;
    public void updateOrderAdmin(String orderid, String statusid) throws OrderServiceException;
    public Order_model getOrder(String orderid) throws OrderServiceException;
}
