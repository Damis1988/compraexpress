package blocojava.compraexpress.repository;

import blocojava.compraexpress.model.Menu;
import blocojava.compraexpress.model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    Menu findMenuByRestaurant_Id(Long id);
}
