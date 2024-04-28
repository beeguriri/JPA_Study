package wendy.study.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.params.UpdateItemParams;

@Slf4j
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

    //아이템 수정 메서드
    public void updateBook(UpdateItemParams params) {
        this.updateItem(params);
        this.author = params.getAuthor() == null ? author : params.getAuthor();
        this.isbn = params.getIsbn() == null ? isbn : params.getIsbn();
    }
}
