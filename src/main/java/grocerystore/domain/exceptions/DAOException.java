package grocerystore.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by raxis on 30.12.2016.
 */
public class DAOException extends Exception{
    private static final Logger logger = LoggerFactory.getLogger(DAOException.class);


    public DAOException(String message){
        super(message);
    }

    public DAOException(String message, Exception e){
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
