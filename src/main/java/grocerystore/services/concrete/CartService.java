package grocerystore.services.concrete;

import grocerystore.domain.abstracts.IRepositoryGrocery;
import grocerystore.domain.entityes.Grocery;
import grocerystore.domain.models.Grocery_model;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.services.abstracts.ICartService;
import grocerystore.services.exceptions.CartServiceException;
import grocerystore.services.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by raxis on 29.12.2016.
 * Класс для работы с корзиной покупок
 */
@Service
public class CartService implements ICartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private IRepositoryGrocery groceryHandler;

    public CartService(IRepositoryGrocery groceryHandler){
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
            groceryModel = convert(groceryHandler.getOne(UUID.fromString(groceryid)));
        } catch (DAOException e) {
            logger.error("cant groceryModel.getOne",e);
            throw new CartServiceException("Невозможно добавить продукт в корзину!",e);
        }

        if(groceryModel !=null){
            cart.addItem(groceryModel,1);
        }
    }

    /**
     * Удаление продукта из корзины по коду продукта
     * @param cart
     * @param groceryid
     */
    @Override
    @Secured("ROLE_USER")
    public void removeFromCart(Cart cart, String groceryid) throws CartServiceException {
        Grocery_model groceryModel = null;
        try {
            groceryModel = convert(groceryHandler.getOne(UUID.fromString(groceryid)));
        } catch (DAOException e) {
            logger.error("cant groceryModel.getOne",e);
            throw new CartServiceException("Невозможно удалить продукт из корзины!",e);
        }
        if(groceryModel !=null){
            cart.removeItem(groceryModel);
        }
    }

    private Grocery_model convert(Grocery grocery){
        Grocery_model grocery_model = new Grocery_model();
        grocery_model.setId(grocery.getId());
        grocery_model.setParentid(grocery.getParentid());
        grocery_model.setName(grocery.getName());
        grocery_model.setPrice(grocery.getPrice());
        grocery_model.setIscategory(grocery.isIscategory());
        grocery_model.setQuantity(grocery.getQuantity());

        return grocery_model;
    }
}
