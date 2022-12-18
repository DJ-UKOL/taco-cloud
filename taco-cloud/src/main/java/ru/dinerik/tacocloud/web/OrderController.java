package ru.dinerik.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.dinerik.tacocloud.TacoOrder;

// Контроллер, предоставляющий форму заказа тако
@Slf4j          // Аннотация для создания logger во время компиляции
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    // Обработка отправки заказа
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();        // сеанс будет очищен и готов к приему нового заказа
        return "redirect:/";
    }
}
