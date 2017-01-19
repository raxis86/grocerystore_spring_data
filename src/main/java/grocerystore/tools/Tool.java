package grocerystore.tools;

import javafx.util.converter.BigDecimalStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

/**
 * Created by raxis on 23.12.2016.
 */
public class Tool {
    private static final Logger logger = LoggerFactory.getLogger(Tool.class);
    private static final PasswordEncoder BCRYPT = new BCryptPasswordEncoder();

    public static String computeHash(String password){
        return BCRYPT.encode(password);
    }


    public static void main(String[] args) {

    }

}
