package ru.dinerik.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.TacoOrder;

import java.util.Date;
import java.util.List;

// Связь между Taco и Ingredient
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    // (Create, Read, Update, Delete)
    // получить список всех заказов, доставленных в заданный район
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    // запросить все заказы, доставленные в определенный район в определенном диапазоне дат
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate);
}