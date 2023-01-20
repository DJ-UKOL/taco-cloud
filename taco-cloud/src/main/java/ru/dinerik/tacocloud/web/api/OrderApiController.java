package ru.dinerik.tacocloud.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.tacocloud.OrderMessagingService;
import ru.dinerik.tacocloud.TacoOrder;
import ru.dinerik.tacocloud.data.OrderRepository;

@RestController
@RequestMapping(path="/api/orders", produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class OrderApiController {
    private OrderRepository repo;
    private OrderMessagingService messageService;
    public OrderApiController(
            OrderRepository repo,
            OrderMessagingService messageService) {
        this.repo = repo;
        this.messageService = messageService;
    }
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        messageService.sendOrder(order);
        return repo.save(order);
    }

    @GetMapping(produces="application/json")
    public Iterable<TacoOrder> allOrders() {
        return repo.findAll();
    }

}
