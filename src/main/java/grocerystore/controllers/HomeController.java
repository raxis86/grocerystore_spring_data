package grocerystore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by raxis on 31.12.2016.
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"/Index","/",""})
    public String index(){
        return "index";
    }

    @RequestMapping({"/Deny"})
    public String deny(){
        return "deadend";
    }

}
