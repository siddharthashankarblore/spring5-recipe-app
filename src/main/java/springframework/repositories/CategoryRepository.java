package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
