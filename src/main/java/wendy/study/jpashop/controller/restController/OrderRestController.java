package wendy.study.jpashop.controller.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.model.Order;
import wendy.study.jpashop.params.OrderItemParam;
import wendy.study.jpashop.params.OrderSearchParam;
import wendy.study.jpashop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    public Long createOrder(@RequestParam Long memberId,
                            @RequestBody List<OrderItemParam> params) {
        return orderService.createOrder(memberId, params);
    }

    @PostMapping("/cancelOrder")
    public void cancelOrder(@RequestParam Long orderId) {
        orderService.cancelOrder(orderId);
    }

    @GetMapping("/findById")
    public Order findOrderById(@RequestParam Long orderId) {
        return orderService.findOrderById(orderId);
    }

    @GetMapping("/findByParam")
    public List<Order> findOrderBySearchParam(@RequestBody OrderSearchParam param) {
        return orderService.findOrderBySearchParam(param);
    }
}
