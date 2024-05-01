package wendy.study.jpashop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "ORDER_ITEM")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"order", "item"}, callSuper = true)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID") //order(1) : orderItem(n)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    //OrderItem(n) : Item(1)
    //orderItem -> Item을 조회하지만
    //Item -> OrderItem 조회할 일은 거의 없을 것 같아서 단방향으로 설정
    private Item item;

    private int orderPrice;
    private int count;

    //생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //주문아이템에 추가하면 아이템의 재고수량을 줄인다
        item.removeStock(count);

        return orderItem;
    }

    //주문을 취소하면 item 재고수량 증가
    public void cancelOrderItem() {
        getItem().addStock(count);
    }

    //주문 조회 시 해당아이템의 전체 가격 조회
    public int getTotalPriceOrderItem() {
        return getOrderPrice() * getCount();
    }

}
