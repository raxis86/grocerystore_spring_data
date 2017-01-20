package grocerystore.controllers;

import grocerystore.services.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(FormUserException.class)
    public ModelAndView formUserExceptionHandler(FormUserException e, HttpServletRequest request){
        ModelAndView model = new ModelAndView(request.getRequestURI().split("/")[1].toLowerCase());
        model.addObject("messages",e.getExceptionMessage().getMessagesError());
        return model;
    }

    @ExceptionHandler(FormGroceryException.class)
    public ModelAndView formGroceryExceptionHandler(FormGroceryException e, HttpServletRequest request){
        String s = request.getParameter("groceryid");

        if(s==null){
            ModelAndView model = new ModelAndView("redirect:GroceryAdd");
            model.addObject("messages", e.getExceptionMessage().getMessagesError());
            return model;
        }
        else {
            ModelAndView model = new ModelAndView("redirect:GroceryEdit");
            model.addObject("messages", e.getExceptionMessage().getMessagesError());
            model.addObject("groceryid",s);
            return model;
        }
    }
}
