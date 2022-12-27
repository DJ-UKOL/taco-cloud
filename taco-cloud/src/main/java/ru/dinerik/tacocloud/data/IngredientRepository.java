package ru.dinerik.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    // Эти методы уже есть в CrudRepository
/*    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);*/
}
