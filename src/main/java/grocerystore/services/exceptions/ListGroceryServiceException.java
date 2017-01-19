package grocerystore.services.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class ListGroceryServiceException extends ServiceException {
    private static final Logger logger = LoggerFactory.getLogger(ListGroceryServiceException.class);

    public ListGroceryServiceException(String message) {
        super(message);
    }

    public ListGroceryServiceException(String message, Exception e) {
        super(message, e);
    }
}
