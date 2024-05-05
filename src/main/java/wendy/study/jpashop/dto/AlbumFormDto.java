package wendy.study.jpashop.dto;

import lombok.Data;
import wendy.study.jpashop.model.item.Album;

import java.time.LocalDateTime;

@Data
public class AlbumFormDto {
    private Long id;
    private String name;
    private String dType = "ALBUM";
    private String artist;
    private String etc;

    private int price;
    private int stockQuantity;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    //엔티티를 dto로 변환
    public AlbumFormDto (Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.artist = album.getArtist();
        this.etc = album.getEtc();
        this.price = album.getPrice();
        this.stockQuantity = album.getStockQuantity();
        this.createdAt = album.getCreatedDate();
        this.modifiedAt = album.getModifiedDate();
    }
}
