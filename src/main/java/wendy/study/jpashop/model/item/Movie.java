package wendy.study.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wendy.study.jpashop.model.Item;

@Data
@Entity
@DiscriminatorValue("MOVIE")
@EqualsAndHashCode(callSuper = true)
public class Movie extends Item {
    private String director;
    private String actor;
}
