package wendy.study.jpashop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wendy.study.jpashop.exception.NotEnoughStockException;
import wendy.study.jpashop.params.UpdateItemParam;

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

    //변경감지 수정메서드
    public void updateItem(UpdateItemParam params) {
        this.name = params.getName() == null ? name : params.getName();
        this.price = params.getPrice() == null ? this.getPrice() : params.getPrice();
        this.stockQuantity = params.getStockQuantity() == null ? this.getStockQuantity() : params.getStockQuantity();
    }

    //수정 v2
    public void updateItem(Item item) {
        this.name = item.getName() == null || item.getName().isEmpty() || item.getName().isBlank() ? name : item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
    }

    /**
     * 다대다 매핑
     */
//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();
}
