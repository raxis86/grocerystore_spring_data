package grocerystore.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 30.12.2016.
 */
public class ListGroceryException extends DAOException {
    private static final Logger logger = LoggerFactory.getLogger(ListGroceryException.class);

    public ListGroceryException(String message, Exception e) {
        super(message,e);
    }
}
