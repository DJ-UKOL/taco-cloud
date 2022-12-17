package ru.dinerik.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.tacocloud.Ingredient;
import ru.dinerik.tacocloud.Ingredient.Type;
import ru.dinerik.tacocloud.Taco;
import ru.dinerik.tacocloud.TacoOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute         // Атрибут для передачи данных в объект модели указанной в @SessionAttributes
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Type.values();
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
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);    // Добавляем полученный объект из формы в объект TacoOrder
        log.info("Processing taco: {}", taco);  // Записываем в журнал

        return "redirect:/orders/current";
    }
}