package blocojava.compraexpress.repository;

import blocojava.compraexpress.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends CrudRepository<Address, Long> {
}
