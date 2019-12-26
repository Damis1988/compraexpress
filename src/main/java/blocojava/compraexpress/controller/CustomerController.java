package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.Address;
import blocojava.compraexpress.model.Customer;
import blocojava.compraexpress.repository.AddressRepository;
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
    @Autowired
    AddressRepository addressRepository;

    // encrypts password
    public String passwordCript(String password) {
        String passwordCript;
        passwordCript = cryptWithMD5.cryptWithMD5(password);
        return passwordCript;
    }

    // manages request for account creation page
    @GetMapping(value = "create")
    public String viewCreate(Map<String, Object> model){
        model.put("message", null);
        model.put("success", false);
        model.put("onGoing", null);
        return "account/create";
    }

    // saves new account to repository
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
        // checks if email is not already in use
        if (customerRepository.findByEmail(email) != null){
            model.put("message", "Email already registered");
            model.put("success", false);
            model.put("onGoing", customer);
            return;
        }
        customer.setEmail(email);
        // confirms if both password input fileds match
        if (!password.equals(confirm_password)){
            model.put("message", "Passwords do not match");
            model.put("success", false);
            model.put("onGoing", customer);
            return;
        }
        password = passwordCript(password);
        customer.setPassword(password);
        // saves address to repository; address is not mandatory
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
                addressRepository.save(address);

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

    // manages request for account updating page
    @GetMapping(value = "secure/update")
    public String viewUpdate(Map<String, Object> model){
        Customer customer = customerRepository.findOne(customerSession.getLoggedUser().getId());
        model.put("customer", customer);
        model.put("message", null);
        model.put("onGoing", null);
        return "secure/account/update";
    }

    // updates account values in repository
    @PostMapping(value = "secure/update")
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

        model.put("message", "");
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
                model.put("message", "Could not update email. Email already in use. ");
            }
        }
        if (StringUtils.hasText(password) && StringUtils.hasText(confirm_password)){
            if (password.equals(confirm_password)){
                customer.setPassword(password);
            } else {
                model.put("message", model.get("message") + "Could not update password. Confirmation password did not match.");
            }
        }
        return customer;
    }

    // manages request for account display page
    @GetMapping(value = "secure/view")
    public String viewAccount(Map<String, Object> model){
        Customer customer = customerRepository.findOne(customerSession.getLoggedUser().getId());
        model.put("customer", customer);
        return "secure/account/view";
    }

    // deletes account
    @GetMapping(value = "secure/delete")
    public String delete(){
        Customer customer = customerRepository.findOne(customerSession.getLoggedUser().getId());
        customerRepository.delete(customer);
        customerSession.removeLoggedUser();
        return "redirect:/login/doLogin";
    }
}
