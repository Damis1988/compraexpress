package blocojava.compraexpress.repository;

import blocojava.compraexpress.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Boolean findByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);
}
