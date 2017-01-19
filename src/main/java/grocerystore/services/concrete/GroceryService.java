package grocerystore.services.concrete;

import grocerystore.domain.abstracts.IRepositoryGrocery;
import grocerystore.domain.abstracts.IRepositoryListGrocery;
import grocerystore.domain.entityes.Grocery;
import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.models.Grocery_model;
import grocerystore.domain.models.ListGrocery_model;
import grocerystore.domain.exceptions.DAOException;
import grocerystore.domain.exceptions.ListGroceryException;
import grocerystore.services.abstracts.IGroceryService;
import grocerystore.services.exceptions.FormGroceryException;
import grocerystore.services.exceptions.GroceryServiceException;
import grocerystore.services.exceptions.ValidateException;
import grocerystore.services.models.Message;
import grocerystore.services.validators.abstracts.IValidator;
import javafx.util.converter.BigDecimalStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 29.12.2016.
 * Работа с продуктами
 */
@Service
public class GroceryService implements IGroceryService {
    private static final Logger logger = LoggerFactory.getLogger(GroceryService.class);

    private IRepositoryGrocery groceryHandler;
    private IRepositoryListGrocery listGroceryHandler;
    private IValidator groceryNameValidator;
    private IValidator priceValidator;
    private IValidator quantityValidator;

    public GroceryService(IRepositoryGrocery groceryHandler,
                          IRepositoryListGrocery listGroceryHandler,
                          IValidator groceryNameValidator,
                          IValidator priceValidator,
                          IValidator quantityValidator){
        this.groceryHandler = groceryHandler;
        this.listGroceryHandler = listGroceryHandler;
        this.groceryNameValidator=groceryNameValidator;
        this.priceValidator=priceValidator;
        this.quantityValidator=quantityValidator;
    }


    @Override
    public List<Grocery_model> getGroceryList() throws GroceryServiceException {
        List<Grocery_model> groceryModelList =new ArrayList<>();
        try {
            for(Grocery g: groceryHandler.getAll()){
                if(g!=null)groceryModelList.add(convert(g));
            }
        } catch (DAOException e) {
            logger.error("cant getGroceryList ",e);
            throw new GroceryServiceException("Невозможно получить список продуктов!",e);
        }
        return groceryModelList;
    }

    @Override
    public Grocery_model getGrocery(String groceryid) throws GroceryServiceException {
        Grocery_model groceryModel;
        try {
            groceryModel =convert(groceryHandler.getOne(UUID.fromString(groceryid)));
        } catch (DAOException e) {
            logger.error("cant getGrocery",e);
            throw new GroceryServiceException("Продукт не найлен!",e);
        }
        return groceryModel;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void groceryCreate(String name, String price, String quantity) throws GroceryServiceException, FormGroceryException {
        Grocery_model groceryModel = new Grocery_model();
        Message message = new Message();

        try {
            groceryNameValidator.validate(name);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }
        try {
            priceValidator.validate(price);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }
        try {
            quantityValidator.validate(quantity);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }

        if(!message.isOk()){
            throw new FormGroceryException(message);
        }

        groceryModel.setId(UUID.randomUUID());
        groceryModel.setIscategory(false);
        groceryModel.setParentid(new UUID(0L,0L));
        groceryModel.setName(name);
        groceryModel.setPrice(new BigDecimalStringConverter().fromString(price));
        groceryModel.setQuantity(Integer.parseInt(quantity));

        try {
            groceryHandler.create(convert(groceryModel));
        } catch (DAOException e) {
            logger.error("cant groceryCreate",e);
            throw new GroceryServiceException("Невозможно сохранить новый продукт!",e);
        }
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void groceryDelete(String groceryid) throws GroceryServiceException {
        Grocery_model groceryModel;
        List<ListGrocery_model> listGroceries = null;

        try {
            groceryModel = convert(groceryHandler.getOne(UUID.fromString(groceryid)));
            for(ListGrocery lg:listGroceryHandler.getListByGroceryId(groceryModel.getId())){
                if(lg!=null)listGroceries.add(convert(lg));
            }
            groceryHandler.delete(groceryModel.getId());
            for(ListGrocery_model gl : listGroceries){
                listGroceryHandler.delete(gl.getId());
            }
        } catch (ListGroceryException e) {
            logger.error("cant getListByGeroceryId",e);
            throw new GroceryServiceException("Невозможно получить список соответствий продуктов!",e);
        } catch (DAOException e) {
            logger.error("cant groceryDelete");
            throw new GroceryServiceException("Невозможно удалить продукт!",e);
        }

    }

    @Override
    @Secured("ROLE_ADMIN")
    public void groceryUpdate(String groceryid, String name, String price, String quantity) throws GroceryServiceException, FormGroceryException {
        Grocery_model groceryModel = null;
        Message message = new Message();

        try {
            groceryNameValidator.validate(name);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }
        try {
            priceValidator.validate(price);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }
        try {
            quantityValidator.validate(quantity);
        } catch (ValidateException e) {
            message.addErrorMessage(e.getMessage());
        }

        if(!message.isOk()){
            throw new FormGroceryException(message);
        }

        try {
            groceryModel =convert(groceryHandler.getOne(UUID.fromString(groceryid)));

            if(groceryModel !=null){
                groceryModel.setName(name);
                groceryModel.setPrice(new BigDecimalStringConverter().fromString(price));
                groceryModel.setQuantity(Integer.parseInt(quantity));

                groceryHandler.update(convert(groceryModel));
            }
            else {
                throw new FormGroceryException(new Message("Продукт не найден в базе!", Message.Status.ERROR));
            }
        } catch (DAOException e) {
            logger.error("cant groceryUpdate",e);
            throw new GroceryServiceException("Невозможно изменить информацию о продукте!",e);
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

    private Grocery convert(Grocery_model grocery_model){
        Grocery grocery = new Grocery();
        grocery.setId(grocery_model.getId());
        grocery.setParentid(grocery_model.getParentid());
        grocery.setName(grocery_model.getName());
        grocery.setPrice(grocery_model.getPrice());
        grocery.setIscategory(grocery_model.isIscategory());
        grocery.setQuantity(grocery_model.getQuantity());

        return grocery;
    }

    private ListGrocery_model convert(ListGrocery listGrocery){
        ListGrocery_model listGrocery_model = new ListGrocery_model();
        listGrocery_model.setId(listGrocery.getId());
        listGrocery_model.setGroceryId(listGrocery.getGroceryId());
        listGrocery_model.setQuantity(listGrocery.getQuantity());

        return listGrocery_model;
    }
}
