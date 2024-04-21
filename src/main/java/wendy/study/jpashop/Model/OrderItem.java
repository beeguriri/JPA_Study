package wendy.study.jpashop.Model;

import jakarta.persistence.*;
import lombok.Data;
import wendy.study.jpashop.Model.common.OrderStatus;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    private Order order;

    private Item item;

    private int orderPrice;
    private int count;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;
}
