package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn //defualt : DTYPE
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;

    //양방향 연관관계 메서드
    public void addCategoryItem (CategoryItem categoryItem) {
        categoryItem.setItem(this);
        categoryItems.add(categoryItem);
    }

    /**
     * 다대다 매핑
     */
//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();
}
