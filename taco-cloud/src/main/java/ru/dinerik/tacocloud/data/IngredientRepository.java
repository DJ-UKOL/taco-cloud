package ru.dinerik.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    // (Create, Read, Update, Delete)
}
