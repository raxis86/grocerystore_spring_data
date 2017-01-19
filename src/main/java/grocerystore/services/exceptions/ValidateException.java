package grocerystore.services.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 09.01.2017.
 */
public class ValidateException extends Exception{
    private static final Logger logger = LoggerFactory.getLogger(ValidateException.class);

    public ValidateException(String message){
        super(message);
    }
}
