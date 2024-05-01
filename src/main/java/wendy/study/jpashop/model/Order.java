package wendy.study.jpashop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wendy.study.jpashop.model.type.DeliveryStatus;
import wendy.study.jpashop.model.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@Entity
@Table(name = "ORDERS")
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //member(1) : order(n)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //order(1) : orderItem(n)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //order(1) : delivery(1) 주인이 order
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

    //생성 메서드
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        orderItems.forEach(order::addOrderItem);
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    //주문 취소
    public void cancelOrder() {
        if(delivery.getDeliveryStatus().equals(DeliveryStatus.COMPLETE))
            throw new RuntimeException("이미 배송 완료된 상품은 취소가 불가능합니다.");

        this.setOrderStatus(OrderStatus.CANCEL);
        orderItems.forEach(OrderItem::cancelOrderItem);

    }

    //주문 조회
    public int getTotalPriceOrder() {
        return orderItems.stream().flatMapToInt(i -> IntStream.of(i.getTotalPriceOrderItem())).sum();
    }
}
