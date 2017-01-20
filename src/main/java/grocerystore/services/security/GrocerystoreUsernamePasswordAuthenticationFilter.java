package grocerystore.services.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by raxis on 20.01.2017.
 */
/*@Service("UsernamePasswordAuthenticationFilter")*/
public class GrocerystoreUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(GrocerystoreUsernamePasswordAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String id = request.getParameter("captcha");
        HttpSession session = request.getSession();
        int rand1 = Integer.parseInt((String) session.getAttribute("rand1"));
        int rand2 = Integer.parseInt((String) session.getAttribute("rand2"));
        if (Integer.valueOf(id) != rand1 + rand2) {
            throw new AuthenticationServiceException("Введите правильный ответ: "+rand1+ "+"+rand2);
        }
        return super.attemptAuthentication(request, response);
    }
}
