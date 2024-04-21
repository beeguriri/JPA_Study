package wendy.study.jpashop.Model;

import jakarta.persistence.*;
import lombok.Data;
import wendy.study.jpashop.Model.common.OrderStatus;
import wendy.study.jpashop.Model.common.RoleType;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;
}
