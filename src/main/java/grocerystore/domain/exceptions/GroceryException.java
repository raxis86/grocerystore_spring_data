package grocerystore.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 30.12.2016.
 */
public class GroceryException extends DAOException {
    private static final Logger logger = LoggerFactory.getLogger(GroceryException.class);

    public GroceryException(String message, Exception e) {
        super(message, e);
    }
}
