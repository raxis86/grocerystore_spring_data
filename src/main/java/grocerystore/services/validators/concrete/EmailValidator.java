package grocerystore.services.validators.concrete;

import grocerystore.services.exceptions.ValidateException;
import grocerystore.services.validators.abstracts.IValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by raxis on 10.01.2017.
 */
@Component
public class EmailValidator implements IValidator{
    private static final Logger logger = LoggerFactory.getLogger(EmailValidator.class);

    @Override
    public boolean validate(String field) throws ValidateException {
        if(!field.toLowerCase().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")){
            throw new ValidateException("email некорректен!");
        }
        else return true;
    }
}
