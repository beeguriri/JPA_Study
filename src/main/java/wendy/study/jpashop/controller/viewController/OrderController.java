package wendy.study.jpashop.controller.viewController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.dto.ItemDto;
import wendy.study.jpashop.dto.MemberFormDto;
import wendy.study.jpashop.dto.OrderDto;
import wendy.study.jpashop.dto.OrderItemDto;
import wendy.study.jpashop.params.ItemSearchParam;
import wendy.study.jpashop.params.OrderSearchParam;
import wendy.study.jpashop.service.ItemService;
import wendy.study.jpashop.service.MemberService;
import wendy.study.jpashop.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("v2/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("")
    public String orderList(@ModelAttribute("param") OrderSearchParam param, Model model) {
        List<OrderDto> orders = orderService.findOrderBySearchParam(param).stream().map(OrderDto::new).toList();
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    @GetMapping("/new")
    public String createOrder(@ModelAttribute("param") ItemSearchParam param, Model model) {
        List<MemberFormDto> members = memberService.findMembers().stream().map(MemberFormDto::new).toList();
        List<ItemDto> items = itemService.searchAllItems(param).stream().filter((item) -> item.getStockQuantity() > 0).toList();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "orders/orderForm";
    }

    @PostMapping("/new")
    public @ResponseBody ResponseEntity<String> createOrder(@RequestBody OrderItemDto orderItems) {
        log.info("order Item::: {}", orderItems);
        try {
            orderService.createOrder(orderItems.getMemberId(), orderItems.getItems());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
