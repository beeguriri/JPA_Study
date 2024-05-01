package wendy.study.jpashop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.model.Order;
import wendy.study.jpashop.model.OrderItem;
import wendy.study.jpashop.model.type.DeliveryStatus;
import wendy.study.jpashop.model.type.OrderStatus;
import wendy.study.jpashop.params.OrderItemParam;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired ItemService itemService;

    @Test
    @DisplayName("정상 주문 - 1건의 주문 아이템")
//    @Rollback(false)
    void createOrder() {
        //given
        Long memberId = 1L;
        OrderItemParam params = new OrderItemParam();
        params.setItemId(2L);
        params.setCount(10);

        Item item = itemService.findItem(params.getItemId());
        int stock = item.getStockQuantity();
        int price = item.getPrice();

        //when
        Long orderId = orderService.createOrder(memberId, List.of(params));

        //then
        Order order = orderService.getOrder(orderId);

        //상품 주문시 상태는 ORDER
        assertEquals(OrderStatus.ORDER, order.getOrderStatus());

        //배송 상태는 READY
        assertEquals(DeliveryStatus.READY, order.getDelivery().getDeliveryStatus());

        //주문 가격 확인
        assertEquals(price * params.getCount(), order.getTotalPriceOrder());

        //아이템 수량은 감소해야 한다.
        assertEquals(stock - params.getCount(), item.getStockQuantity());

    }

    @Test
    @DisplayName("정상 주문 - 2건의 주문 아이템")
//    @Rollback(false)
    void createOrder2() {
        //given
        Long memberId = 1L;
        List<OrderItemParam> params = new ArrayList<>();
        params.add(new OrderItemParam(2L, 30));
        params.add(new OrderItemParam(3L, 30));

        //when
        Long orderId = orderService.createOrder(memberId, params);

        //then
        Order order = orderService.getOrder(orderId);

        //상품 주문시 상태는 ORDER
        assertEquals(OrderStatus.ORDER, order.getOrderStatus());

        //배송 상태는 READY
        assertEquals(DeliveryStatus.READY, order.getDelivery().getDeliveryStatus());

        //전체 주문 가격 확인
        int totalPrice = 0;
        for(OrderItemParam param : params) {
            totalPrice += itemService.findItem(param.getItemId()).getPrice() * param.getCount();
        }
        assertEquals(totalPrice, order.getTotalPriceOrder());
    }

    @Test
    @DisplayName("주문 취소")
//    @Rollback(false)
    void cancelOrder() {

        //given
        Long orderId = 2L;
        Order order = orderService.getOrder(orderId);

        OrderItem orderItem = order.getOrderItems().get(0);
        int count = orderItem.getCount();
        Item item = itemService.findItem(orderItem.getItem().getId());
        int stockQuantity = item.getStockQuantity();

        //when
        orderService.cancelOrder(orderId);

        //then
        //주문 상태는 취소
        assertEquals(OrderStatus.CANCEL, order.getOrderStatus());

        //재고수량 원복
        assertEquals(stockQuantity + count, item.getStockQuantity());

    }

    @Test
    void getItem() {
        Long orderId = 2L;
        Order order = orderService.getOrder(orderId);
        System.out.println(order);
    }
}