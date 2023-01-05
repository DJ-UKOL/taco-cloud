package ru.dinerik.tacocloud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

// Класс предоставляющий ингредиенты тако
@Data        // Для генерации основных методов гет и сет, конструкторы и т.д.
@Entity      // для JPA
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}