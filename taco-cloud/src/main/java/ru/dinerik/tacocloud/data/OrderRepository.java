package ru.dinerik.tacocloud.data;

import ru.dinerik.tacocloud.TacoOrder;

// Связь между Taco и Ingredient
public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}