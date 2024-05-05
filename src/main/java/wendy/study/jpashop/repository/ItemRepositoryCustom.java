package wendy.study.jpashop.repository;

import wendy.study.jpashop.dto.ItemDto;

import java.util.List;

public interface ItemRepositoryCustom {
    List<ItemDto> searchAllItems();
}
