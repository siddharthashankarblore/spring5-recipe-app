package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
