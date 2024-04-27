package wendy.study.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import wendy.study.jpashop.model.Item;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Item {

    private String author;
    private String isbn;

}
