package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.Address;
import blocojava.compraexpress.model.Customer;
import blocojava.compraexpress.repository.CustomerRepository;
import blocojava.compraexpress.security.CryptWithMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("account")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CryptWithMD5 cryptWithMD5;
    @Autowired
    CustomerSession customerSession;

    String msg = "";

    public String passwordCript(String password) {
        String passwordCript;
        passwordCript = cryptWithMD5.cryptWithMD5(password);
        return passwordCript;
    }

    @GetMapping(value = "create")
    public String viewCreate(Map<String, Object> model){
        model.put("message", null);
        model.put("success", false);
        model.put("onGoing", null);
        return "account/create";
    }

    @PostMapping(value = "create")
    public void save(@RequestParam("name") String name,
                     @RequestParam("surname") String surname,
                     @RequestParam("cpf") String cpf,
                     @RequestParam("email") String email,
                     @RequestParam("password") String password,
                     @RequestParam("confirm_password") String confirm_password,
                     @RequestParam("street") String street,
                     @RequestParam("number") String number,
                     @RequestParam("complement") String complement,
                     @RequestParam("zip") String zip,
                     @RequestParam("neighborhood") String neighborhood,
                     @RequestParam("city") String city,
                     @RequestParam("state") String state,
                     @RequestParam("country") String country,
                     Map<String,Object> model) {

        Customer customer = new Customer();
        if (StringUtils.hasText(name) && StringUtils.hasText(surname) &&
                StringUtils.hasText(cpf)) {
            customer.setName(name);
            customer.setSurname(surname);
            customer.setCpf(cpf);
        } else {
            model.put("message", "Fill all the required fields.");
            return;
        }

        if (customerRepository.findByEmail(email) != null){
            model.put("message", "Email already registered");
            model.put("success", false);
            model.put("onGoing", customer);
            return;
        }
        customer.setEmail(email);

        if (!password.equals(confirm_password)){
            model.put("message", "Passwords do not match");
            model.put("success", false);
            model.put("onGoing", customer);
            return;
        }
        password = passwordCript(password);
        customer.setPassword(password);

        if (StringUtils.hasText(street) || StringUtils.hasText(number)
                || StringUtils.hasText(complement) || StringUtils.hasText(zip)
                || StringUtils.hasText(neighborhood) || StringUtils.hasText(city)
                || StringUtils.hasText(state) || StringUtils.hasText(country)) {
            if (StringUtils.hasText(street) && StringUtils.hasText(number)
                    && StringUtils.hasText(complement) && StringUtils.hasText(zip)
                    && StringUtils.hasText(neighborhood) && StringUtils.hasText(city)
                    && StringUtils.hasText(state) && StringUtils.hasText(country)) {
                Address address = new Address();
                address.setStreet(street);
                address.setNumber(number);
                address.setComplement(complement);
                address.setZip(zip);
                address.setNeighborhood(neighborhood);
                address.setCity(city);
                address.setState(state);
                address.setCountry(country);
                customer.setAddress(address);
            } else {
                model.put("message", "Oops, fill all the fields in order to register an address.\nRegistering an address is optional.");
                return;
            }
        }

        customerRepository.save(customer);
        model.put("message", "You have been successfully registered");
        model.put("success", true);
        model.put("onGoing", null);
        return;
    }

    @GetMapping(value = "update")
    public String viewUpdate(Map<String, Object> model){
        model.put("customer", customerSession.getLoggedUser());
        model.put("message", null);
        model.put("onGoing", null);
        msg = "";
        return "secure/account/update";
    }

    @PostMapping(value = "update")
    public Customer update(@RequestParam("id") Long id,
                       @RequestParam("name") String name,
                       @RequestParam("surname") String surname,
                       @RequestParam("cpf") String cpf,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("confirm_password") String confirm_password,
                       @RequestParam("street") String street,
                       @RequestParam("number") String number,
                       @RequestParam("complement") String complement,
                       @RequestParam("zip") String zip,
                       @RequestParam("neighborhood") String neighborhood,
                       @RequestParam("city") String city,
                       @RequestParam("state") String state,
                       @RequestParam("country") String country,
                       Map<String,Object> model) {

        Customer customer = customerRepository.findOne(id);

        if (StringUtils.hasText(name)){customer.setName(name);}
        if (StringUtils.hasText(surname)){customer.setSurname(surname);}
        if (StringUtils.hasText(cpf)){customer.setCpf(cpf);}

        Address address = customer.getAddress();
        if (StringUtils.hasText(street)){address.setStreet(street);}
        if (StringUtils.hasText(number)){address.setNumber(number);}
        if (StringUtils.hasText(complement)){address.setComplement(complement);}
        if (StringUtils.hasText(zip)){address.setZip(zip);}
        if (StringUtils.hasText(neighborhood)){address.setNeighborhood(neighborhood);}
        if (StringUtils.hasText(city)){address.setCity(city);}
        if (StringUtils.hasText(state)){address.setState(state);}
        if (StringUtils.hasText(country)){address.setCountry(country);}
        customer.setAddress(address);

        if (StringUtils.hasText(email)){
            if (customerRepository.findByEmail(email) == null){
                customer.setEmail(email);
            } else {
                msg += "Could not update email. Email already in use. ";
                model.put("message", msg);
            }
        }
        if (StringUtils.hasText(password) && StringUtils.hasText(confirm_password)){
            if (password.equals(confirm_password)){
                customer.setPassword(password);
            } else {
                msg += "Could not update password. Confirmation password did not match. ";
                model.put("message", msg);
            }
        }
        return customer;
    }

    @GetMapping(value = "view")
    public String viewAccount(Map<String, Object> model){
        model.put("customer", customerSession.getLoggedUser());
        return "secure/account/view";
    }

    @PostMapping(value = "delete")
    public String delete(){
        customerRepository.delete(customerSession.getLoggedUser());
        customerSession.removeLoggedUser();
        return "redirect:/login/doLogin";
    }
}
