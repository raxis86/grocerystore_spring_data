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
public class GroceryNameValidator implements IValidator {
    private static final Logger logger = LoggerFactory.getLogger(GroceryNameValidator.class);

    @Override
    public boolean validate(String field) throws ValidateException {
        if("".equals(field)){
            throw new ValidateException("Наименование не должно быть пустым!");
        }
        else {
            return true;
        }
    }
}
