package ru.dinerik.tacocloud;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Класс, представляющий заказ
@Data
@Entity
public class TacoOrder implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt = new Date();

    // Информация для доставки
    @NotBlank(message="Delivery name is required")
    private String ccCVV;

    // Список объектов Тако составляющих заказ
    @OneToMany(cascade = CascadeType.ALL)       // при удалении заказа все связанные с ним тако тоже будут удалены
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}