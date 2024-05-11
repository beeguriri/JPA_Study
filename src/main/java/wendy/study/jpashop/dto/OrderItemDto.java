package wendy.study.jpashop.dto;

import lombok.Data;
import wendy.study.jpashop.params.OrderItemParam;

import java.util.List;

@Data
public class OrderItemDto {
    private Long memberId;
    private List<OrderItemParam> items;
}
