package wendy.study.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import wendy.study.jpashop.model.Item;

@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Item {
    private String director;
    private String actor;
}
