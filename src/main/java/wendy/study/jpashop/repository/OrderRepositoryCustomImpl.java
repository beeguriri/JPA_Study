package wendy.study.jpashop.repository;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;
import wendy.study.jpashop.model.Order;
import wendy.study.jpashop.model.QItem;
import wendy.study.jpashop.params.OrderSearchParam;

import java.util.List;

import static wendy.study.jpashop.model.QMember.member;
import static wendy.study.jpashop.model.QOrder.order;
import static wendy.study.jpashop.model.QOrderItem.orderItem;

public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

    public OrderRepositoryCustomImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> searchOrder(OrderSearchParam param) {

        JPQLQuery<Order> query = from(order)
                .leftJoin(order.member, member).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .leftJoin(orderItem.item, QItem.item).fetchJoin();

        if(StringUtils.hasText(param.getUserName()))
                    query.where(member.name.contains(param.getUserName()));

        if(param.getOrderStatus() != null)
            query.where(order.orderStatus.eq(param.getOrderStatus()));

        return query.stream().toList();
    }
}
