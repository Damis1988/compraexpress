package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("secure")
public class SecureController {

    @Autowired
    CustomerSession customerSession;

    // redirects secure requests to either the login page or the main page
    @GetMapping(value = "")
    public String main(Map<String,Object> model) {
        if (customerSession.getLoggedUser() == null) {
            model.put("message", null);
            return "login/doLogin";
        } else {
            return "redirect:/secure/restaurant/listRestaurant";
        }
    }
}
