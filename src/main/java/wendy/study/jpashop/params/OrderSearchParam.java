package wendy.study.jpashop.params;

import lombok.Data;
import wendy.study.jpashop.model.type.OrderStatus;

@Data
public class OrderSearchParam {
    private String userName;
    private OrderStatus orderStatus;
}
