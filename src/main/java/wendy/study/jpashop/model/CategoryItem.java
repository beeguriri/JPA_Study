package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ITEM_ID")
    private Long id;

    // Item (1) : Category_Item (n)
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // Category(1) : Category_Item (n)
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;

}
