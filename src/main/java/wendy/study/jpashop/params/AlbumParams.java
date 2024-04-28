package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.item.Album;

@Data
public class AlbumParams {
    private String name;
    private int price;
    private int stockQuantity;
    private String artist;
    private String etc;

    public Album toEntity() {
        return Album.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .artist(artist)
                .etc(etc)
                .build();
    }
}