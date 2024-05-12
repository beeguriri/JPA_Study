package wendy.study.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wendy.study.jpashop.model.*;
import wendy.study.jpashop.model.type.DeliveryStatus;
import wendy.study.jpashop.params.OrderItemParam;
import wendy.study.jpashop.params.OrderSearchParam;
import wendy.study.jpashop.repository.ItemRepository;
import wendy.study.jpashop.repository.MemberRepository;
import wendy.study.jpashop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    // 주문생성
    @Transactional
    public Long createOrder(Long memberId, List<OrderItemParam> params) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setDeliveryStatus(DeliveryStatus.READY);

        //주문아이템 정보 생성
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemParam param : params) {
            Item item = itemRepository.findById(param.getItemId()).orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));
            OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), param.getCount());
            orderItems.add(orderItem);
        }

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItems);

        orderRepository.save(order);

        return order.getId();
    }

    // 주문취소
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("존재하지 않는 주문입니다."));
        //해당엔티티 취소
        order.cancelOrder();
    }

    // 주문조회
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("존재하지 않는 주문입니다."));
    }

    // 특정 키워드로 조회
    public List<Order> findOrderBySearchParam(OrderSearchParam param) {
        return orderRepository.searchOrder(param);
    }



}
