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
@DiscriminatorValue("ALBUM")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Album extends Item {

    private String artist;
    private String etc;

    @Builder
    public Album(String name, int price, int stockQuantity, String artist, String etc) {
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }
}
