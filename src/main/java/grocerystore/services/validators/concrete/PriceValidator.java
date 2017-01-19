package grocerystore.services.validators.concrete;

import grocerystore.services.exceptions.ValidateException;
import grocerystore.services.validators.abstracts.IValidator;
import javafx.util.converter.BigDecimalStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by raxis on 09.01.2017.
 */
@Component
public class PriceValidator implements IValidator {
    private static final Logger logger = LoggerFactory.getLogger(PriceValidator.class);

    @Override
    public boolean validate(String field) throws ValidateException {
        try {
            new BigDecimalStringConverter().fromString(field);
        } catch (NumberFormatException e){
            throw new ValidateException("Формат цены неверен!");
        }
        return true;
    }
}
