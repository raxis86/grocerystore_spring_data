package grocerystore.services.validators.abstracts;

import grocerystore.services.exceptions.BusinessLogicException;
import grocerystore.services.exceptions.ValidateException;

/**
 * Created by raxis on 06.01.2017.
 */
public interface IValidator {

    public boolean validate(String field) throws ValidateException;
}
