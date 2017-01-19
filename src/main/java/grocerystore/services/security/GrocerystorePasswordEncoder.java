package grocerystore.services.security;

import grocerystore.tools.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by raxis on 13.01.2017.
 */
public class GrocerystorePasswordEncoder implements PasswordEncoder {
    private static final Logger logger = LoggerFactory.getLogger(GrocerystorePasswordEncoder.class);
    private static final PasswordEncoder BCRYPT = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return BCRYPT.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCRYPT.matches(rawPassword, encodedPassword);
    }
}
