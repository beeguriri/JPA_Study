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
@DiscriminatorValue("BOOK")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
