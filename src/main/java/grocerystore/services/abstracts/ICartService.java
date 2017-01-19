package grocerystore.services.abstracts;

import grocerystore.domain.exceptions.DAOException;
import grocerystore.services.exceptions.CartServiceException;
import grocerystore.services.models.Cart;

/**
 * Created by raxis on 29.12.2016.
 */
public interface ICartService {
    public void addToCart(Cart cart, String groceryid) throws CartServiceException;
    public void removeFromCart(Cart cart, String groceryid) throws CartServiceException;
}
