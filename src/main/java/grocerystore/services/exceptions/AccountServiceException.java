package grocerystore.services.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class AccountServiceException extends ServiceException{
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceException.class);

    public AccountServiceException(String message) {
        super(message);
    }

    public AccountServiceException(String message, Exception e) {
        super(message, e);
    }
}
