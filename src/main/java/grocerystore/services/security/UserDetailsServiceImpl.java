package grocerystore.services.security;

import grocerystore.domain.entityes.Role;
import grocerystore.domain.entityes.User;
import grocerystore.domain.models.User_model;
import grocerystore.domain.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by raxis on 13.01.2017.
 */
@Service("userDetailsService")
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        try {
            user = userRepository.findOneByEmail(email); //our own User_model model class
        } catch (Exception e) {
            logger.error("cant getOneByEmail",e);
            throw new UsernameNotFoundException("Пользователь не найден!");
        }

        if(user !=null){
            //String password = Tool.computeHash(Tool.computeHash(userModel.getPassword()) + userModel.getSalt());
            String password = user.getPassword();
            //additional information on the security object
            boolean enabled = user.getStatus().equals(User_model.Status.ACTIVE.toString());
            boolean accountNonExpired = user.getStatus().equals(User_model.Status.ACTIVE.toString());
            boolean credentialsNonExpired = user.getStatus().equals(User_model.Status.ACTIVE.toString());
            boolean accountNonLocked = user.getStatus().equals(User_model.Status.ACTIVE.toString());

            //Let's populate userModel roles
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for(Role roleModel : user.getRoles()){
                authorities.add(new SimpleGrantedAuthority(roleModel.getRoleName()));

            }

            //Now let's create Spring Security User_model object
            /*org.springframework.security.core.userdetails.User securityUser = new
                    org.springframework.security.core.userdetails.User(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            return securityUser;*/

            return new org.springframework.security.core.userdetails.User(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        }else{
            throw new UsernameNotFoundException("User_model Not Found!!!");
        }
    }
}
