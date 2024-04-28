package wendy.study.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wendy.study.jpashop.model.Item;

@Data
@Entity
@DiscriminatorValue("MOVIE")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Movie extends Item {

    private String director;
    private String actor;

    @Builder
    public Movie(String name, int price, int stockQuantity, String director, String actor) {
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.director = director;
        this.actor = actor;
    }
}
