package ru.dinerik.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.TacoOrder;

// Связь между Taco и Ingredient
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}