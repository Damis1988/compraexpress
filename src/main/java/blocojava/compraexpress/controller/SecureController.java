package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("secure")
public class SecureController {

    @Autowired
    CustomerSession customerSession;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main(Map<String,Object> model) {
        model.put("customer", customerSession.getLoggedUser());
        return "login/doLogin";
    }
}
