package grocerystore.services.exceptions;

import grocerystore.services.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class BusinessLogicException extends Exception{
    private static final Logger logger = LoggerFactory.getLogger(BusinessLogicException.class);

    private Message message;

    public BusinessLogicException(Message message){
        this.message=message;
    }

    public Message getExceptionMessage() {
        return message;
    }
}
