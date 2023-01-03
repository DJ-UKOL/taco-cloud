package ru.dinerik.tacocloud;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

// Класс предоставляющий ингредиенты тако
@Data        // Для генерации основных методов гет и сет, конструкторы и т.д.
@AllArgsConstructor
@NoArgsConstructor
@Table("ingredients")
public class Ingredient {

    @PrimaryKey
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}