package wendy.study.jpashop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import wendy.study.jpashop.model.item.Album;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlbumFormDto {
    private Long id;
    @NotBlank
    @Size(min = 1, max = 20, message = "상품명은 1자 이상 10자 이하로 입력해야 합니다.")
    private String name;
    private String dType = "ALBUM";
    @NotBlank
    @Size(min = 1, max = 20, message = "가수명은 1자 이상 10자 이하로 입력해야 합니다.")
    private String artist;
    private String etc;

    @Range(min = 0, max = 100000, message = "가격은 최소 0원부터 최대 100,000원까지 입력할 수 있습니다.")
    private int price;
    @Range(min = 0, max = 1000, message = "재고는 최소 0개부터 최대 1000개까지 입력할 수 있습니다.")
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

    //dto를 엔티티로 변환
    public Album toEntity() {
        return Album.builder()
                .name(this.name)
                .artist(this.artist)
                .etc(this.etc == null ? null : this.etc)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }
}
