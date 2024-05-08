package wendy.study.jpashop.controller.viewController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import wendy.study.jpashop.model.Order;
import wendy.study.jpashop.params.OrderSearchParam;
import wendy.study.jpashop.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("v2/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public String orderList(@ModelAttribute("param") OrderSearchParam param, Model model) {
        List<Order> orders = orderService.findOrderBySearchParam(param);
        model.addAttribute("orders", orders);
        return "orders/list";
    }
}
