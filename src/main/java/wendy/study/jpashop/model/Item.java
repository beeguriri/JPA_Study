package wendy.study.jpashop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wendy.study.jpashop.exception.NotEnoughStockException;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn //defualt : DTYPE
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class Item extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    //양방향 연관관계 메서드
    public void addCategoryItem (CategoryItem categoryItem) {
        categoryItem.setItem(this);
        categoryItems.add(categoryItem);
    }

    //비즈니스 로직
    public void addStock (int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock (int quantity) {

        int result = this.stockQuantity - quantity;
        if(result < 0) throw new NotEnoughStockException("재고 수량이 부족 합니다.");

        this.stockQuantity = result;
    }

    /**
     * 다대다 매핑
     */
//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();
}
