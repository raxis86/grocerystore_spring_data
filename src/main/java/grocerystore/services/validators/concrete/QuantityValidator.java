package grocerystore.services.validators.concrete;

import grocerystore.services.exceptions.ValidateException;
import grocerystore.services.validators.abstracts.IValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by raxis on 09.01.2017.
 */
@Component
public class QuantityValidator implements IValidator {
    private static final Logger logger = LoggerFactory.getLogger(QuantityValidator.class);

    @Override
    public boolean validate(String field) throws ValidateException {
        try {
            Integer.parseInt(field);
        } catch (NumberFormatException e){
            throw new ValidateException("Формат количества неверен!");
        }
        return true;
    }
}
