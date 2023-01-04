package ru.dinerik.tacocloud.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.tacocloud.Ingredient;
import ru.dinerik.tacocloud.Ingredient.Type;
import ru.dinerik.tacocloud.Taco;
import ru.dinerik.tacocloud.TacoOrder;
import ru.dinerik.tacocloud.TacoUDT;
import ru.dinerik.tacocloud.data.IngredientRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder") // Атрибут для передачи данных в объект модели указанной в @SessionAttributes
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")  // Атрибут для передачи данных в объект модели указанной в @SessionAttributes
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(i->i.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);    // Добавляем полученный объект из формы в объект TacoOrder

        return "redirect:/orders/current";
    }
}