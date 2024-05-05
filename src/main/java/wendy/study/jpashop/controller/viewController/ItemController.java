package wendy.study.jpashop.controller.viewController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import wendy.study.jpashop.dto.AlbumFormDto;
import wendy.study.jpashop.dto.BookFormDto;
import wendy.study.jpashop.dto.ItemDto;
import wendy.study.jpashop.dto.MovieFormDto;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.model.item.Album;
import wendy.study.jpashop.model.item.Book;
import wendy.study.jpashop.model.item.Movie;
import wendy.study.jpashop.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("v2/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("")
    public String itemList(Model model) {
        List<ItemDto> items = itemService.searchAllItems();
        model.addAttribute("items", items);
        return "items/list";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        if (item instanceof Album) {
            AlbumFormDto album = new AlbumFormDto((Album) item);
            model.addAttribute("album", album);
            return "items/albumForm";
        }
        if (item instanceof Book) {
            BookFormDto book = new BookFormDto((Book) item);
            model.addAttribute("book", book);
            return "items/bookForm";
        }
        if (item instanceof Movie) {
            MovieFormDto movie = new MovieFormDto((Movie) item);
            model.addAttribute("movie", movie);
            return "items/movieForm";
        }
        return "items/list";
    }
}
