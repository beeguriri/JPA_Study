package wendy.study.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.params.OrderItemParams;
import wendy.study.jpashop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    public Long createOrder(@RequestParam Long memberId,
                            @RequestBody List<OrderItemParams> params) {
        return orderService.createOrder(memberId, params);
    }

    @PostMapping("/cancelOrder")
    public void cancelOrder(@RequestParam Long orderId) {
        orderService.cancelOrder(orderId);
    }
}
