package grocerystore.controllers;

import grocerystore.services.abstracts.IAccountService;
import grocerystore.services.abstracts.IUserService;
import grocerystore.services.exceptions.AccountServiceException;
import grocerystore.services.exceptions.FormUserException;
import grocerystore.services.exceptions.UserServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*
// * Created by raxis on 31.12.2016.


@Controller
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private IAccountService accountService;
    private IUserService userService;

    public AccountController(IAccountService accountService,IUserService userService){
        this.accountService=accountService;
        this.userService=userService;
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value="/Logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:Index";
    }

    @RequestMapping(value = "Signin", method = RequestMethod.GET)
    public String signin(){
        return "signin";
    }

    @RequestMapping(value = "Signin", method = RequestMethod.POST)
    public String signin(@RequestParam("email") String email, @RequestParam("password") String password,
                         @RequestParam("name") String name, @RequestParam("lastname") String lastname,
                         @RequestParam("surname") String surname, @RequestParam("phone") String phone,
                         @RequestParam("address") String address)
            throws UserServiceException, FormUserException, AccountServiceException {

        boolean isSignin=false;

        isSignin = accountService.signIn(userService.formUser(email,password,name,lastname,
                surname,address,phone,"ROLE_USER"));

        if(isSignin){
            return "signinsuccess";
        }
        else {
            return "signin";
        }
    }
}
