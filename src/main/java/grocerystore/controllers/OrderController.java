package grocerystore.controllers;

import grocerystore.domain.models.User_model;
import grocerystore.services.abstracts.IListGroceryService;
import grocerystore.services.abstracts.IOrderService;
import grocerystore.services.abstracts.IUserService;
import grocerystore.services.exceptions.FormUserException;
import grocerystore.services.exceptions.ListGroceryServiceException;
import grocerystore.services.exceptions.OrderServiceException;
import grocerystore.services.exceptions.UserServiceException;
import grocerystore.services.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by raxis on 02.01.2017.
 */
@Controller
@SessionAttributes(value = {"user","role","cart"})
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private IOrderService orderService;
    private IListGroceryService listGroceryService;
    private IUserService userService;

    public OrderController(IOrderService orderService,IListGroceryService listGroceryService,IUserService userService){
        this.orderService=orderService;
        this.listGroceryService=listGroceryService;
        this.userService=userService;
    }

    @ModelAttribute("user")
    public User_model populateUser() throws UserServiceException, FormUserException {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User_model userModel = userService.formUserFromRepo(principal.getUsername());
        return userModel;
    }

    @RequestMapping(value = "OrderList", method = RequestMethod.GET)
    public String list(@ModelAttribute("user") User_model userModel, Model model)
                       throws OrderServiceException {


        if(userModel !=null){
            model.addAttribute("orderlist",orderService.formOrderViewList(userModel));
            return "orderlist";
        }
        else {
            return "deadend";
        }
    }

    @RequestMapping(value = "OrderList", method = RequestMethod.POST)
    public ModelAndView list(@RequestParam("orderid") String orderid)
                             throws OrderServiceException {

        orderService.updateOrder(orderid);
        return new ModelAndView("redirect:OrderList");
    }

    @RequestMapping(value = "OrderListAdmin", method = RequestMethod.GET)
    public String listAdmin(Model model) throws OrderServiceException {

        model.addAttribute("orderlist",orderService.formOrderViewListAdmin());
        return "orderlist_admin";
    }

    @RequestMapping(value = "OrderEdit", method = RequestMethod.GET)
    public String edit(@RequestParam("orderid") String orderid, Model model)
                        throws OrderServiceException {

        model.addAttribute("order",orderService.formOrderView(orderid));
        return "orderedit";
    }

    @RequestMapping(value = "OrderEdit", method = RequestMethod.POST)
    public String edit(@RequestParam("orderid") String orderid,@RequestParam("statusid") String statusid,Model model)
                       throws OrderServiceException {
        orderService.updateOrderAdmin(orderid,statusid);
        model.addAttribute("orderlist",orderService.formOrderViewListAdmin());
        return "orderlist_admin";
    }

    @RequestMapping(value = "OrderAdd", method = RequestMethod.GET)
    public String add(@ModelAttribute("user") User_model userModel, @ModelAttribute("cart") Cart cart, Model model){
        if((cart!=null)&&(userModel !=null)){
            model.addAttribute("user", userModel);
            model.addAttribute("cart",cart);
            model.addAttribute("totalprice",cart.computeTotalPrice().toString());
            return "orderadd";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "OrderAdd", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User_model userModel, @ModelAttribute("cart") Cart cart,
                      @RequestParam("name") String name, @RequestParam("lastname") String lastname,
                      @RequestParam("surname") String surname, @RequestParam("address") String address,
                      @RequestParam("phone") String phone)
                      throws UserServiceException,
                             OrderServiceException,
                             ListGroceryServiceException {

        if((cart!=null)&&(userModel !=null)){
            userService.updateUser(userModel,name,lastname,surname,address,phone);
            listGroceryService.createListGrocery(cart,orderService.createOrder(userModel,cart));
            cart.clear();
            return "ordersuccess";
        }
        else {
            return "index";
        }
    }
}
