package ru.dinerik.tacocloud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

// Класс предоставляющий ингредиенты тако
@Data        // Для генерации основных методов гет и сет, конструкторы и т.д.
@Entity      // для JPA
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}