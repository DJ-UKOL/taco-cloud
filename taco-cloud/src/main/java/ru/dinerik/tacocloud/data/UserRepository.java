package ru.dinerik.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.dinerik.tacocloud.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);           // поиск учетной записи по имени пользователя
}
