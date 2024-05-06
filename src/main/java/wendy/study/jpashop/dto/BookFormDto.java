package wendy.study.jpashop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import wendy.study.jpashop.model.item.Book;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookFormDto {
    private Long id;
    @NotEmpty
    @Size(min = 1, max = 20, message = "상품명은 1자 이상 10자 이하로 입력해야 합니다.")
    private String name;
    private String dType = "BOOK";
    @Size(min = 1, max = 20, message = "작가명은 1자 이상 10자 이하로 입력해야 합니다.")
    private String author;
    @NotEmpty (message = "ISBN은 필수값 입니다.")
    private String isbn;

    @Range(min = 0, max = 100000, message = "가격은 최소 0원부터 최대 100,000원까지 입력할 수 있습니다.")
    private int price;
    @Range(min = 0, max = 1000, message = "재고는 최소 0개부터 최대 1000개까지 입력할 수 있습니다.")
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

    public Book toEntity() {
        return Book.builder()
                .name(this.name)
                .author(this.author)
                .isbn(this.isbn)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }
}
