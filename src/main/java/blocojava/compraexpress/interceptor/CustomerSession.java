package blocojava.compraexpress.interceptor;

import blocojava.compraexpress.model.Customer;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomerSession {

    Customer customer;

    public void addLoggedUser(Customer c) {this.customer = c;}

    public Customer getLoggedUser() {return customer;}

    public void removeLoggedUser() {this.customer = null;}
}
