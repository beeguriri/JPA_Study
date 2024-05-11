package wendy.study.jpashop.repository;

import wendy.study.jpashop.dto.ItemDto;
import wendy.study.jpashop.params.ItemSearchParam;

import java.util.List;

public interface ItemRepositoryCustom {
    List<ItemDto> searchAllItems(ItemSearchParam param);
}
