package ru.dinerik.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.Taco;

public interface TacoRepository
        extends CrudRepository<Taco, Long> {
}
