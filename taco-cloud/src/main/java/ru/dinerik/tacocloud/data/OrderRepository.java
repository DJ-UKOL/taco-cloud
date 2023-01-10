package ru.dinerik.tacocloud.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.TacoOrder;
import ru.dinerik.tacocloud.User;

import java.util.List;

// Связь между Taco и Ingredient
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc (User user, Pageable pageable);
}