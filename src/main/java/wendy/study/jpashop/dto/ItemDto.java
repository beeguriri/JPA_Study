package wendy.study.jpashop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String dType;
    private Date createdAt;
    private Date modifiedAt;

    @Builder
    public ItemDto(Long id, String name, int price, int stockQuantity, String dType, Date createdAt, Date modifiedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.dType = dType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
