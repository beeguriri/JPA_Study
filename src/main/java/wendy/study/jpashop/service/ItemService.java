package wendy.study.jpashop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wendy.study.jpashop.dto.ItemDto;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.model.item.Album;
import wendy.study.jpashop.model.item.Book;
import wendy.study.jpashop.model.item.Movie;
import wendy.study.jpashop.params.UpdateItemParam;
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

    //native query 사용한 itemDto
    public List<ItemDto> searchAllItems() {
        return itemRepository.searchAllItems();
    }

    //상품삭제
    @Transactional
    public void deleteItem(Long id) {
        itemRepository.delete(findItem(id));
    }

    //상품수정
    @Transactional
    public void updateItem(Long id, UpdateItemParam params) {
        //우선 아이템을 찾는다
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException("찾는 아이템이 없습니다."));

        //각 아이템의 인스턴스에 따라 수정
        if(item instanceof Book) {
            ((Book) item).updateBook(params);
            return;
        }

        if(item instanceof Album) {
            ((Album) item).updateAlbum(params);
            return;
        }

        if(item instanceof Movie) {
            ((Movie) item).updateMovie(params);
            return;
        }
    }

    //상품 수정 v2
    @Transactional
    public void updateItem(Long id, Item item) {
        Item before = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException("찾는 아이템이 없습니다."));
        //각 아이템의 인스턴스에 따라 수정
        if(item instanceof Album) {
            ((Album) before).updateAlbum((Album) item);
            return;
        }
        if(item instanceof Book) {
            ((Book) before).updateBook((Book) item);
            return;
        }
        if(item instanceof Movie) {
            ((Movie) before).updateMovie((Movie) item);
            return;
        }
    }
}
