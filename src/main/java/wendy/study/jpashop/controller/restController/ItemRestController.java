package wendy.study.jpashop.controller.restController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.params.AlbumParam;
import wendy.study.jpashop.params.BookParam;
import wendy.study.jpashop.params.MovieParam;
import wendy.study.jpashop.params.UpdateItemParam;
import wendy.study.jpashop.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemRestController {

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
    public Long insertItem(@RequestBody AlbumParam params) {
        return itemService.insertItem(params.toEntity());
    }

    @PostMapping("/insert/book")
    public Long insertItem(@RequestBody BookParam params) {
        return itemService.insertItem(params.toEntity());
    }

    @PostMapping("/insert/movie")
    public Long insertItem(@RequestBody MovieParam params) {
        return itemService.insertItem(params.toEntity());
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PutMapping("/{itemId}/edit")
    public void updateItem(@PathVariable("itemId") Long itemId,
                                        @RequestBody UpdateItemParam updateItemParam) {
        itemService.updateItem(itemId, updateItemParam);
    }

}
