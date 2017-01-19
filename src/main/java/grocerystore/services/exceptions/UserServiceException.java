package grocerystore.services.exceptions;

import grocerystore.services.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class UserServiceException extends ServiceException {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceException.class);

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Exception e) {
        super(message, e);
    }
}
