package grocerystore.services.exceptions;

import grocerystore.services.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 30.12.2016.
 */
public class FormUserException extends BusinessLogicException {
    private static final Logger logger = LoggerFactory.getLogger(FormUserException.class);

    public FormUserException(Message message) {
        super(message);
    }
}
