package wendy.study.jpashop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import wendy.study.jpashop.model.item.Movie;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MovieFormDto {
    private Long id;
    @NotEmpty
    @Size(min = 1, max = 20, message = "상품명은 1자 이상 10자 이하로 입력해야 합니다.")
    private String name;
    private String dType = "MOVIE";
    @Size(min = 1, max = 20, message = "감독 이름은 1자 이상 10자 이하로 입력해야 합니다.")
    private String director;
    @Size(min = 1, max = 20, message = "배우 이름은 1자 이상 20자 이하로 입력해야 합니다.")
    private String actor;

    @Range(min = 0, max = 100000, message = "가격은 최소 0원부터 최대 100,000원까지 입력할 수 있습니다.")
    private int price;
    @Range(min = 0, max = 1000, message = "재고는 최소 0개부터 최대 1000개까지 입력할 수 있습니다.")
    private int stockQuantity;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    //엔티티를 dto로 변환
    public MovieFormDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
        this.price = movie.getPrice();
        this.stockQuantity = movie.getStockQuantity();
        this.createdAt = movie.getCreatedDate();
        this.modifiedAt = movie.getModifiedDate();
    }

    public Movie toEntity() {
        return Movie.builder()
                .name(this.name)
                .director(this.director)
                .actor(this.actor)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }
}
