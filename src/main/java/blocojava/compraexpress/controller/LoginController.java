package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.Customer;
import blocojava.compraexpress.repository.CustomerRepository;
import blocojava.compraexpress.security.CryptWithMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerSession customerSession;
    @Autowired
    CryptWithMD5 cryptWithMD5;

    // encrypts password
    public String passwordCript(String password) {
        String passwordCript;
        passwordCript = cryptWithMD5.cryptWithMD5(password);
        return passwordCript;
    }

    // manages request for login page
    @GetMapping (value = "doLogin")
    public String loginPage() {
        return "login/doLogin";
    }

    // logs customer to session
    @PostMapping(value = "doLogin")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        Map<String, Object> model) {
        password = passwordCript(password);
        Customer customer = customerRepository.findByEmailAndPassword(email,password);
        if (customer != null) {
            customerSession.addLoggedUser(customer);
            return "redirect:/secure/restaurant/listRestaurant";
        } else {
            model.put("message", "Login not valid");
            return null;
        }
    }

    // logs customer as guest to session
    @GetMapping(value = "guest")
    public String guestLogIn(){
        customerSession.guestLogIn();
        return "redirect:/secure/restaurant/listRestaurant";
    }

    // clears session
    @GetMapping(value = "secure/logout")
    public String logout() {
        customerSession.removeLoggedUser();
        return "redirect:/login/doLogin";
    }
}

