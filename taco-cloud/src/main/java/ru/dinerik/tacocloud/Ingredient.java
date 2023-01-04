package ru.dinerik.tacocloud;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.mongodb.core.mapping.Document;

// Класс предоставляющий ингредиенты тако
@Data        // Для генерации основных методов гет и сет, конструкторы и т.д.
@Document(collection="ingredients")
@AllArgsConstructor
@NoArgsConstructor
@Table("ingredients")
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}