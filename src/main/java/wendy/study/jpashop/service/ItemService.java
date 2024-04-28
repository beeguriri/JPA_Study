package wendy.study.jpashop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.repository.ItemRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    //상품등록
    @Transactional
    public Long insertItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    //상품조회
    public Item findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalStateException("찾는 아이템이 없습니다."));
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    //상품삭제
    public void deleteItem(Long id) {
        itemRepository.delete(findItem(id));
    }
}
