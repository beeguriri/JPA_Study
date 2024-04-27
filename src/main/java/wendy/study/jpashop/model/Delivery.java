package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;
import wendy.study.jpashop.model.type.DeliveryStatus;

@Data
@Entity
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    @OneToOne(mappedBy = "delivery") //order(1) : delivery(1) 주인이 order
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

}
