package grocerystore.services.exceptions;

import grocerystore.services.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 06.01.2017.
 */
public class ServiceException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(ServiceException.class);

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Exception e){
        super(message,e);
    }

    @Override
    public String getMessage() {
        if(super.getCause()!=null) {
            return super.getMessage() + "\n::\n" + super.getCause().getMessage();
        }
        else {
            return super.getMessage();
        }
    }
}
