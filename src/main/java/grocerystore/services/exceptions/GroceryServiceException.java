package grocerystore.services.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class GroceryServiceException extends ServiceException {
    private static final Logger logger = LoggerFactory.getLogger(GroceryServiceException.class);

    public GroceryServiceException(String message) {
        super(message);
    }

    public GroceryServiceException(String message, Exception e) {
        super(message, e);
    }
}
