package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;
import wendy.study.jpashop.model.type.DeliveryStatus;

import java.time.LocalDateTime;

@Data
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    @OneToOne(mappedBy = "order") //order(1) : delivery(1) 주인이 order
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;

}
