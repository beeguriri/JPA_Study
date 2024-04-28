package wendy.study.jpashop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.params.AlbumParams;
import wendy.study.jpashop.params.BookParams;
import wendy.study.jpashop.params.MovieParams;
import wendy.study.jpashop.params.UpdateItemParams;
import wendy.study.jpashop.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public Item findItem(@PathVariable("itemId") Long itemId) {
        return itemService.findItem(itemId);
    }

    @GetMapping("/all")
    public List<Item> findItems() {
        return itemService.findItems();
    }

    @PostMapping("/insert/album")
    public Long insertItem(@RequestBody AlbumParams params) {
        return itemService.insertItem(params.toEntity());
    }

    @PostMapping("/insert/book")
    public Long insertItem(@RequestBody BookParams params) {
        return itemService.insertItem(params.toEntity());
    }

    @PostMapping("/insert/movie")
    public Long insertItem(@RequestBody MovieParams params) {
        return itemService.insertItem(params.toEntity());
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PutMapping("/{itemId}/edit")
    public void updateItem(@PathVariable("itemId") Long itemId,
                                        @RequestBody UpdateItemParams updateItemParams) {
        itemService.updateItem(itemId, updateItemParams);
    }

}
