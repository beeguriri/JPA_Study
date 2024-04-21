package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID") //order(1) : orderItem(n)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    //OrderItem(n) : Item(1)
    //orderItem -> Item을 조회하지만
    //Item -> OrderItem 조회할 일은 거의 없을 것 같아서 단방향으로 설정
    private Item item;

    private int orderPrice;
    private int count;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;
}