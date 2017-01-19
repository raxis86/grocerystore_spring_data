package grocerystore.services.concrete;

import grocerystore.domain.abstracts.IRepositoryListGrocery;
import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.models.Grocery_model;
import grocerystore.domain.models.ListGrocery_model;
import grocerystore.domain.models.Order_model;
import grocerystore.domain.exceptions.DAOException;
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

    private IRepositoryListGrocery listGroceryHandler;

    public ListGroceryService(IRepositoryListGrocery listGroceryHandler){
        this.listGroceryHandler=listGroceryHandler;
    }


    /**
     * Создание списка продуктов соответствующих заказу
     * @param cart
     * @param orderModel
     * @throws DAOException
     */
    @Override
    @Secured("ROLE_USER")
    public void createListGrocery(Cart cart, Order_model orderModel) throws ListGroceryServiceException {
        for(Map.Entry entry : cart.getMap().entrySet()){
            ListGrocery_model listGroceryModel = new ListGrocery_model();
            listGroceryModel.setId(orderModel.getGrocerylistid());
            listGroceryModel.setGroceryId(((Grocery_model)entry.getKey()).getId());
            listGroceryModel.setQuantity((int)entry.getValue());

            try {
                listGroceryHandler.create(convert(listGroceryModel));
            } catch (DAOException e) {
                logger.error("cant create",e);
                throw new ListGroceryServiceException("Невозможно завершить формирование корзины!",e);
            }
        }
    }

    private ListGrocery convert(ListGrocery_model ListGrocery_model){
        ListGrocery listGrocery = new ListGrocery();
        listGrocery.setId(ListGrocery_model.getId());
        listGrocery.setGroceryId(ListGrocery_model.getGroceryId());
        listGrocery.setQuantity(ListGrocery_model.getQuantity());

        return listGrocery;
    }
}
