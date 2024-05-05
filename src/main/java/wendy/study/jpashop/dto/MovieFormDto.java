package wendy.study.jpashop.dto;

import lombok.Data;
import wendy.study.jpashop.model.item.Movie;

import java.time.LocalDateTime;

@Data
public class MovieFormDto {
    private Long id;
    private String name;
    private String dType = "MOVIE";
    private String director;
    private String actor;

    private int price;
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
}
