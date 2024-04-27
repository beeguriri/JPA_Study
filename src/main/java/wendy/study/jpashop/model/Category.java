package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;

    //양방향 연관관계 메서드
    public void addCategoryItem (CategoryItem categoryItem) {
        categoryItem.setCategory(this);
        categoryItems.add(categoryItem);
    }


    /**
     * 다대다 매핑
     */
//    @ManyToMany
//    @JoinTable(name = "CATEGORY_ITEM",
//                joinColumns = @JoinColumn(name = "CATEGORY_ID"),
//                inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
//    private List<Item> items = new ArrayList<>();
//
//    //계층구조 만들기
//    @ManyToOne
//    @JoinColumn(name = "PARENT_ID")
//    private  Category parent;
//
//    @OneToMany(mappedBy = "parent")
//    private List<Category> child = new ArrayList<>();
//
//    //연관관계 메서드
//    public void addChildCategory(Category child) {
//        this.child.add(child);
//        child.setParent(this);
//    }
//
//    public void addItem(Item item) {
//        items.add(item);
//    }

}
