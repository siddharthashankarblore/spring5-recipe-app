package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
