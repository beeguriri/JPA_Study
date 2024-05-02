package wendy.study.jpashop.repository;

import wendy.study.jpashop.model.Order;
import wendy.study.jpashop.params.OrderSearchParam;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> searchOrder(OrderSearchParam param);
}
