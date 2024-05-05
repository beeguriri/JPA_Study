package wendy.study.jpashop.dto;

import lombok.Data;
import wendy.study.jpashop.model.item.Book;

import java.time.LocalDateTime;

@Data
public class BookFormDto {
    private Long id;
    private String name;
    private String dType = "BOOK";
    private String author;
    private String isbn;

    private int price;
    private int stockQuantity;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    //엔티티를 dto로 변환
    public BookFormDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
        this.stockQuantity = book.getStockQuantity();
        this.createdAt = book.getCreatedDate();
        this.modifiedAt = book.getModifiedDate();
    }
}
