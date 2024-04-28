package wendy.study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wendy.study.jpashop.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
