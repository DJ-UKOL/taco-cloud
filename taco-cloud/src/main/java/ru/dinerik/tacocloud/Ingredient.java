package ru.dinerik.tacocloud;

import lombok.Data;

// Класс предоставляющий ингредиенты тако
@Data        // Для генерации основных методов гет и сет, конструкторы и т.д.
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}