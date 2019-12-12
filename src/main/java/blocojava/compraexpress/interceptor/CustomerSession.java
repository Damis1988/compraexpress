package blocojava.compraexpress.interceptor;

import blocojava.compraexpress.model.Customer;
import blocojava.compraexpress.model.Item;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomerSession {

    private Boolean guest = false;
    private Customer customer;
    private List<Item> cart;

    public void addLoggedUser(Customer c) {this.customer = c;}

    public Customer getLoggedUser() {return customer;}

    public void removeLoggedUser() {this.customer = null; this.guest = false;}

    public List<Item> getCart() {return cart;}

    public void guestLogIn() {
        Customer guest = new Customer();
        guest.setName("no_name");
        guest.setSurname(null);
        guest.setEmail(null);
        guest.setPassword(null);
        guest.setAddress(null);
        guest.setCpf(null);
        guest.setPhone(null);
        this.customer = guest;
        this.guest = true;
    }
}
