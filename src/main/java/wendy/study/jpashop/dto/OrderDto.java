package wendy.study.jpashop.dto;

import lombok.Data;
import wendy.study.jpashop.model.Delivery;
import wendy.study.jpashop.model.Order;
import wendy.study.jpashop.model.OrderItem;
import wendy.study.jpashop.model.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String userName;
    private List<OrderItem> orderItems;
    private Delivery delivery;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    //엔티티를 Dto로 변환
    public OrderDto(Order order) {
        this.id = order.getId();
        this.userName = order.getMember().getName();
        this.orderItems = order.getOrderItems();
        this.delivery = order.getDelivery();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getOrderStatus();
    }
}
