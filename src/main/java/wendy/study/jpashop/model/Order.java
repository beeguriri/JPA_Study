package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;
import wendy.study.jpashop.model.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //member(1) : order(n)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order") //order(1) : orderItem(n)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY) //order(1) : delivery(1) 주인이 order
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //양방향 연관관계 메서드 (Member <-> Order)
    public void setMember(Member member) {
        //기존 관계 제거
        if(this.member != null) this.member.getOrders().remove(this);

        this.member = member;
        member.getOrders().add(this);
    }

    //양방향 연관관계 메서드 (Order <-> OrderItem)
    //Order에서 Item 추가하는 로직이 더 많이 발생할 것 같아서 여기에 생성함
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //양방향 연관관계 메서드 (Order <-> Delivery)
    public void setDelivery (Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
