package wendy.study.jpashop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wendy.study.jpashop.dto.ItemDto;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.params.ItemSearchParam;

import java.util.List;

public interface ItemRepositoryCustom {
    List<ItemDto> searchAllItems(ItemSearchParam param);
    Page<Item> searchItemsWithPage(ItemSearchParam param, Pageable pageable);
}
