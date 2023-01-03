package ru.dinerik.tacocloud;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// Класс, предоставляющий рецепт
@Data
@Table("tacos")     // Хранить в таблице "tacos"
public class Taco {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)        // Определение ключа раздела
    private UUID id = Uuids.timeBased();                        // id играет роль ключа раздела

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @PrimaryKeyColumn(type=PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)        // Определение ключа кластеризации
    private Date createdAt = new Date();

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @Column("ingredients")                                              // Отображает список в столбец "ingredients"
    private List<IngredientUDT> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }
}