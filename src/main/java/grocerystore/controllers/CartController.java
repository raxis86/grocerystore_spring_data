package grocerystore.controllers;

import grocerystore.domain.exceptions.DAOException;
import grocerystore.services.abstracts.ICartService;
import grocerystore.services.exceptions.CartServiceException;
import grocerystore.services.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by raxis on 02.01.2017.
 */
@Controller
@SessionAttributes(value = {"cart"})
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private ICartService cartService;

    public CartController(ICartService cartService){
        this.cartService=cartService;
    }

    @ModelAttribute("cart")
    public Cart populateCart()
    {
        return new Cart();
    }

    @RequestMapping(value = "CartList", method = RequestMethod.GET)
    public String list(@ModelAttribute("cart") Cart cart, Model model){
        if(cart!=null){
            model.addAttribute("cart",cart);
            model.addAttribute("totalprice",cart.computeTotalPrice().toString());
            return "cart";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "CartAdd", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("cart") Cart cart, @RequestParam("groceryid") String groceryid)
                            throws CartServiceException {
        cartService.addToCart(cart,groceryid);
        return new ModelAndView("redirect:GroceryList");
    }

    @RequestMapping(value = "CartRemove", method = RequestMethod.POST)
    public ModelAndView remove(@ModelAttribute("cart") Cart cart, @RequestParam("groceryid") String groceryid)
                               throws CartServiceException {
        cartService.removeFromCart(cart,groceryid);
        return new ModelAndView("redirect:CartList");
    }

}
