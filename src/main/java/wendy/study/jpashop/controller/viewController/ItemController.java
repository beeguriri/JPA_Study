package wendy.study.jpashop.controller.viewController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wendy.study.jpashop.dto.ItemDto;
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
}
