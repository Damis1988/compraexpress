package blocojava.compraexpress.repository;

import blocojava.compraexpress.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends CrudRepository<Sector, Long> {
}
