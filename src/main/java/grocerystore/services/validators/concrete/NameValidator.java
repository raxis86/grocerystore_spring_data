package grocerystore.services.validators.concrete;

import grocerystore.services.exceptions.BusinessLogicException;
import grocerystore.services.exceptions.ValidateException;
import grocerystore.services.validators.abstracts.IValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by raxis on 09.01.2017.
 */
@Component
public class NameValidator implements IValidator {
    private static final Logger logger = LoggerFactory.getLogger(NameValidator.class);

    @Override
    public boolean validate(String field) throws ValidateException {
        if("".equals(field)){
            throw new ValidateException("Имя/Фамилия не должно быть пустым!");
        }
        else {
            return true;
        }
    }
}
