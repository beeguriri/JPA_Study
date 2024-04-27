package wendy.study.jpashop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CategoryItem {

    // Item (1) : Category_Item (n)
    @ManyToOne @Id
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // Category(1) : Category_Item (n)
    @ManyToOne @Id
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

}
