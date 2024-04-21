package wendy.study.jpashop.Model;

import jakarta.persistence.*;
import lombok.Data;
import wendy.study.jpashop.Model.common.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne //member(1) : order(n)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order") //order(1) : orderItem(n)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;

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
}
