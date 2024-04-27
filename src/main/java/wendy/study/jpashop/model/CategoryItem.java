package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CategoryItem {

    // Item (1) : Category_Item (n)
    @ManyToOne(fetch = FetchType.LAZY) @Id
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // Category(1) : Category_Item (n)
    @ManyToOne(fetch = FetchType.LAZY) @Id
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

}
