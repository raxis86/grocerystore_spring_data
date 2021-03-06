package grocerystore.services.concrete;

import grocerystore.domain.entityes.Grocery;
import grocerystore.domain.models.Grocery_model;
import grocerystore.domain.repositories.GroceryRepository;
import grocerystore.services.abstracts.ICartService;
import grocerystore.services.exceptions.CartServiceException;
import grocerystore.services.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static grocerystore.services.models.Converter.convert;

/**
 * Created by raxis on 29.12.2016.
 * Класс для работы с корзиной покупок
 */
@Service
public class CartService implements ICartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private GroceryRepository groceryHandler;

    public CartService(GroceryRepository groceryHandler){
        this.groceryHandler=groceryHandler;
    }

    /**
     * Добавление продукта в корзину по коду продукта
     * @param cart - объект корзина
     * @param groceryid - код продукта
     */
    @Override
    @Secured("ROLE_USER")
    public void addToCart(Cart cart, String groceryid) throws CartServiceException {
        Grocery_model groceryModel;

        try {
            groceryModel = convert(groceryHandler.findOne(UUID.fromString(groceryid)));
        } catch (Exception e) {
            logger.error("cant groceryModel.getOne",e);
            throw new CartServiceException("Невозможно добавить продукт в корзину!",e);
        }

        if(groceryModel !=null){
            cart.addItem(groceryModel,1);
        }
    }

    /**
     * Удаление продукта из корзины по коду продукта
     * @param cart Cart of groceries
     * @param groceryid id of grocery
     */
    @Override
    @Secured("ROLE_USER")
    public void removeFromCart(Cart cart, String groceryid) throws CartServiceException {
        Grocery_model groceryModel = null;
        try {
            groceryModel = convert(groceryHandler.findOne(UUID.fromString(groceryid)));
        } catch (Exception e) {
            logger.error("cant groceryModel.getOne",e);
            throw new CartServiceException("Невозможно удалить продукт из корзины!",e);
        }
        if(groceryModel !=null){
            cart.removeItem(groceryModel);
        }
    }

}
