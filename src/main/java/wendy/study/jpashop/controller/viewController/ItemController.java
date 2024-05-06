package wendy.study.jpashop.controller.viewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wendy.study.jpashop.dto.*;
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

    @GetMapping("/new")
    public String addItem() {
        return "items/itemForm";
    }

    @GetMapping("/new/album")
    public String addAlbum(Model model) {
        model.addAttribute("album", new AlbumFormDto());
        return "items/albumForm";
    }

    @PostMapping("/new/album")
    public String addAlbum(@Valid @ModelAttribute("album") AlbumFormDto form,  BindingResult result) {
        log.info(form.toString());
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "items/albumForm";
        }
        itemService.insertItem(form.toEntity());
        return "redirect:/";
    }

    @GetMapping("/new/book")
    public String addBook(Model model) {
        model.addAttribute("book", new BookFormDto());
        return "items/bookForm";
    }

    @PostMapping("/new/book")
    public String addBook(@Valid @ModelAttribute("book") BookFormDto form,  BindingResult result) {
        log.info(form.toString());
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "items/bookForm";
        }
        itemService.insertItem(form.toEntity());
        return "redirect:/";
    }

    @GetMapping("/new/movie")
    public String addMovie(Model model) {
        model.addAttribute("movie", new MovieFormDto());
        return "items/movieForm";
    }

    @PostMapping("/new/movie")
    public String addMovie(@Valid @ModelAttribute("movie") MovieFormDto form,  BindingResult result) {
        log.info(form.toString());
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "items/movieForm";
        }
        itemService.insertItem(form.toEntity());
        return "redirect:/";
    }

    @GetMapping("/update/{itemId}")
    public String updateItem(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        if (item instanceof Album) {
            model.addAttribute("album", new AlbumFormDto((Album) item));
            return "items/albumForm";
        }
        if (item instanceof Book) {
            model.addAttribute("book", new BookFormDto((Book) item));
            return "items/bookForm";
        }
        if (item instanceof Movie) {
            model.addAttribute("album", new MovieFormDto((Movie) item));
            return "items/movieForm";
        }
        return "redirect:/";
    }

    @PostMapping("/update/album/{itemId}")
    public String updateAlbum(@PathVariable Long itemId, @Valid @ModelAttribute("album") AlbumFormDto form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "items/albumForm";
        }
        itemService.updateItem(itemId, form.toEntity());
        return "redirect:/";
    }

    @PostMapping("/update/book/{itemId}")
    public String updateBook(@PathVariable Long itemId, @Valid @ModelAttribute("book") BookFormDto form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "items/bookForm";
        }
        itemService.updateItem(itemId, form.toEntity());
        return "redirect:/";
    }

    @PostMapping("/update/movie/{itemId}")
    public String updateMovie(@PathVariable Long itemId, @Valid @ModelAttribute("movie") MovieFormDto form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("error:::{}", result);
            return "items/movieForm";
        }
        itemService.updateItem(itemId, form.toEntity());
        return "redirect:/";
    }

    //js 알람창 처리를 위하여 responseEntity로 return
    @GetMapping("/delete/{itemId}")
    public @ResponseBody ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        try {
            itemService.deleteItem(itemId);
        } catch (Exception e) {
            log.error("error:::{}", e.getMessage());
            //ajax로 error 처리
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
