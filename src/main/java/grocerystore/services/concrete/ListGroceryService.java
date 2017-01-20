package grocerystore.services.concrete;

import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.models.Grocery_model;
import grocerystore.domain.models.Order_model;
import grocerystore.domain.repositories.ListGroceryRepository;
import grocerystore.services.abstracts.IListGroceryService;
import grocerystore.services.exceptions.ListGroceryServiceException;
import grocerystore.services.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by raxis on 29.12.2016.
 * Работа со списком продуктов в заказе
 */
@Service
public class ListGroceryService implements IListGroceryService {
    private static final Logger logger = LoggerFactory.getLogger(ListGroceryService.class);

    private ListGroceryRepository listGroceryHandler;

    public ListGroceryService(ListGroceryRepository listGroceryHandler){
        this.listGroceryHandler=listGroceryHandler;
    }


    /**
     * Создание списка продуктов соответствующих заказу
     * @param cart Cart of groceries
     * @param orderModel Order from controller
     * @throws ListGroceryServiceException - service exeption
     */
    @Override
    @Secured("ROLE_USER")
    public void createListGrocery(Cart cart, Order_model orderModel) throws ListGroceryServiceException {
        for(Map.Entry entry : cart.getMap().entrySet()){
            ListGrocery listGrocery = new ListGrocery();
            listGrocery.setId(orderModel.getGrocerylistid());
            listGrocery.setGroceryid(((Grocery_model)entry.getKey()).getId());
            listGrocery.setQuantity((int)entry.getValue());

            try {
                listGroceryHandler.save(listGrocery);
            } catch (Exception e) {
                logger.error("cant create",e);
                throw new ListGroceryServiceException("Невозможно завершить формирование корзины!",e);
            }
        }
    }

}
