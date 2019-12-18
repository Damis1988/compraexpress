package blocojava.compraexpress.repository;

import blocojava.compraexpress.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findByMenu_Id(Long id);
}
