package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.item.Movie;

@Data
public class MovieParam {
    private String name;
    private int price;
    private int stockQuantity;
    private String director;
    private String actor;

    public Movie toEntity() {
        return Movie.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .director(director)
                .actor(actor)
                .build();
    }
}
