package ru.dinerik.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.TacoOrder;

import java.util.UUID;

// Связь между Taco и Ingredient
public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}