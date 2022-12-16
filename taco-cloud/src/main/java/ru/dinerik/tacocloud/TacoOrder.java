package ru.dinerik.tacocloud;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// Класс, представляющий заказ
@Data
public class TacoOrder {

    // Информация для доставки
    private String deliverName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    // Информация об оплате
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    // Список объектов Тако составляющих заказ
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
