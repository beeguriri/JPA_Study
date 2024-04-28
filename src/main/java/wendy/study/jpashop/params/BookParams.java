package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.item.Book;

@Data
public class BookParams {
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public Book toEntity() {
        return Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .author(author)
                .isbn(isbn)
                .build();
    }
}
