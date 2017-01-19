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
public class PasswordValidator implements IValidator {
    private static final Logger logger = LoggerFactory.getLogger(PasswordValidator.class);

    @Override
    public boolean validate(String field) throws ValidateException {
        if(field.length()<8){
            throw new ValidateException("Длина пароля не может быть меньше 8 символов!");
        }
        else if (!field.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")){
            throw new ValidateException("Слабый пароль! Пароль должен быть на латиннице, со строчными/прописными буквами и спецсимволами!");
        }
        else {
            return true;
        }

    }
}
