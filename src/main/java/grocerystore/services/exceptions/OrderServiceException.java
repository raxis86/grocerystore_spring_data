package grocerystore.services.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class OrderServiceException extends ServiceException{
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceException.class);

    public OrderServiceException(String message) {
        super(message);
    }

    public OrderServiceException(String message, Exception e) {
        super(message, e);
    }
}
