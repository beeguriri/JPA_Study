package wendy.study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wendy.study.jpashop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
