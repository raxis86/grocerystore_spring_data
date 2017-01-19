package grocerystore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by raxis on 02.01.2017.
 */
@Controller
public class DefaultSpringController {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSpringController.class);

    @RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
    public String forwardRequest() {
        return "mappingerror";
    }
}
