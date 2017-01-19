package grocerystore.services.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class CartServiceException extends ServiceException{
    private static final Logger logger = LoggerFactory.getLogger(CartServiceException.class);

    public CartServiceException(String message) {
        super(message);
    }

    public CartServiceException(String message, Exception e) {
        super(message, e);
    }
}
