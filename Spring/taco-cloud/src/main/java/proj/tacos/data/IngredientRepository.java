package proj.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.tacos.model.Ingredient;

@Repository
public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
