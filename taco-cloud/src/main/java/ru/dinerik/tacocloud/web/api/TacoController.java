package ru.dinerik.tacocloud.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.tacocloud.Taco;
import ru.dinerik.tacocloud.data.TacoRepository;

import java.util.Optional;

/* Контроллер RESTful для обработки запросов к API создания рецептов тако */
@RestController
@RequestMapping(path="/api/tacos", produces="application/json")    // Обрабатывает запросы с путем /api/tacos
@CrossOrigin(origins="http://tacocloud:8080")                          // Разрешает обработку межсайтовых запросов
public class TacoController {

    private final TacoRepository tacoRepo;

    public TacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params="recent")                     // будет обрабатывать GET-запросы для /design?recent
    public Iterable<Taco> recentTacos() {               // Извлекает и возвращает последние рецепты тако
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    // возвращает рецепт тако по его идентификатору
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    // обрабатывает запросы с рецептами тако и сохраняющий их в базе данных
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)         // статус HTTP 201
    // объект Taco извлекается из данных в формате JSON, полученных в теле запроса.
    public Taco postTaco(@RequestBody Taco taco) {      // тело запроса должно быть преобразовано в объект Taco и присвоено параметру.

        return tacoRepo.save(taco);
    }

}