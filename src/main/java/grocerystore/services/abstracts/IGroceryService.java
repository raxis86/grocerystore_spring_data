package grocerystore.services.abstracts;

import grocerystore.domain.models.Grocery_model;
import grocerystore.services.exceptions.FormGroceryException;
import grocerystore.services.exceptions.GroceryServiceException;

import java.util.List;

/**
 * Created by raxis on 29.12.2016.
 */
public interface IGroceryService {
    public List<Grocery_model> getGroceryList() throws GroceryServiceException;
    public Grocery_model getGrocery(String groceryid) throws GroceryServiceException;
    public void groceryCreate(String name, String price, String quantity) throws GroceryServiceException, FormGroceryException;
    public void groceryDelete(String groceryid) throws GroceryServiceException;
    public void groceryUpdate(String groceryid, String name, String price, String quantity) throws GroceryServiceException, FormGroceryException;
}
