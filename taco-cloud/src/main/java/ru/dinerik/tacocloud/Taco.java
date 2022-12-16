package ru.dinerik.tacocloud;

import lombok.Data;

import java.util.List;

// Класс, предоставляющий рецепт
@Data
public class Taco {

    private String name;
    private List<Ingredient> ingredients;
}
