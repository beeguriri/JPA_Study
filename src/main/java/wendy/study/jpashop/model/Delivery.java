package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wendy.study.jpashop.model.type.DeliveryStatus;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery") //order(1) : delivery(1) 주인이 order
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

}
