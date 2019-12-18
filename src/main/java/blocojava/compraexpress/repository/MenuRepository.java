package blocojava.compraexpress.repository;

import blocojava.compraexpress.model.Menu;
import blocojava.compraexpress.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    Menu findMenuByRestaurant_Id(Long id_restaurant);
    Menu findByProducts(List<Product> productList);
}
