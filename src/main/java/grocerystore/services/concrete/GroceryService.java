package grocerystore.services.concrete;

import grocerystore.domain.entityes.Grocery;
import grocerystore.domain.entityes.ListGrocery;
import grocerystore.domain.models.Grocery_model;
import grocerystore.domain.models.ListGrocery_model;
import grocerystore.domain.repositories.GroceryRepository;
import grocerystore.domain.repositories.ListGroceryRepository;
import grocerystore.services.abstracts.IGroceryService;
import grocerystore.services.exceptions.FormGroceryException;
import grocerystore.services.exceptions.GroceryServiceException;
import grocerystore.services.exceptions.ValidateException;
import grocerystore.services.models.Converter;
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

import static grocerystore.services.models.Converter.convert;

/**
 * Created by raxis on 29.12.2016.
 * Работа с продуктами
 */
@Service
public class GroceryService implements IGroceryService {
    private static final Logger logger = LoggerFactory.getLogger(GroceryService.class);

    private GroceryRepository groceryHandler;
    private ListGroceryRepository listGroceryHandler;
    private IValidator groceryNameValidator;
    private IValidator priceValidator;
    private IValidator quantityValidator;

    public GroceryService(GroceryRepository groceryHandler,
                          ListGroceryRepository listGroceryHandler,
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
            for(Grocery g: groceryHandler.findAll()){
                if(g!=null)groceryModelList.add(convert(g));
            }
        } catch (Exception e) {
            logger.error("cant getGroceryList ",e);
            throw new GroceryServiceException("Невозможно получить список продуктов!",e);
        }
        return groceryModelList;
    }

    @Override
    public Grocery_model getGrocery(String groceryid) throws GroceryServiceException {
        Grocery_model groceryModel;
        try {
            groceryModel = convert(groceryHandler.findOne(UUID.fromString(groceryid)));
        } catch (Exception e) {
            logger.error("cant getGrocery",e);
            throw new GroceryServiceException("Продукт не найлен!",e);
        }
        return groceryModel;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void groceryCreate(String name, String price, String quantity) throws GroceryServiceException, FormGroceryException {
        Grocery grocery = new Grocery();
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

        grocery.setId(UUID.randomUUID());
        grocery.setIscategory(false);
        grocery.setParentid(new UUID(0L,0L));
        grocery.setName(name);
        grocery.setPrice(new BigDecimalStringConverter().fromString(price));
        grocery.setQuantity(Integer.parseInt(quantity));

        try {
            groceryHandler.save(grocery);
        } catch (Exception e) {
            logger.error("cant groceryCreate",e);
            throw new GroceryServiceException("Невозможно сохранить новый продукт!",e);
        }
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void groceryDelete(String groceryid) throws GroceryServiceException {
        Grocery grocery;
        List<ListGrocery> listGroceries;

        try {
            grocery = groceryHandler.findOne(UUID.fromString(groceryid));
            listGroceries = (List<ListGrocery>)listGroceryHandler.findAllByGroceryid(grocery.getId());

            groceryHandler.delete(grocery.getId());
            listGroceryHandler.delete(listGroceries);
        } catch (Exception e) {
            logger.error("cant getListByGeroceryId",e);
            throw new GroceryServiceException("Невозможно удалить продукт!",e);
        }

    }

    @Override
    @Secured("ROLE_ADMIN")
    public void groceryUpdate(String groceryid, String name, String price, String quantity) throws GroceryServiceException, FormGroceryException {
        Grocery grocery;
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
            grocery = groceryHandler.findOne(UUID.fromString(groceryid));

            if(grocery !=null){
                grocery.setName(name);
                grocery.setPrice(new BigDecimalStringConverter().fromString(price));
                grocery.setQuantity(Integer.parseInt(quantity));

                groceryHandler.save(grocery);
            }
            else {
                throw new FormGroceryException(new Message("Продукт не найден в базе!", Message.Status.ERROR));
            }
        } catch (Exception e) {
            logger.error("cant groceryUpdate",e);
            throw new GroceryServiceException("Невозможно изменить информацию о продукте!",e);
        }
    }
}
