package ru.dinerik.tacocloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dinerik.tacocloud.Taco;

public interface TacoRepository
        extends JpaRepository<Taco, Long> {

}
