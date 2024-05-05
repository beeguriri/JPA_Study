package wendy.study.jpashop.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import wendy.study.jpashop.dto.ItemDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<ItemDto> searchAllItems() {
        List<Object[]> results = entityManager
                .createNativeQuery("SELECT ITEM_ID, NAME, PRICE, STOCK_QUANTITY, DTYPE, CREATED_DATE, MODIFIED_DATE FROM ITEM")
                .getResultList();

        List<ItemDto> items = new ArrayList<>();
        for (Object[] result : results) {
            ItemDto item = ItemDto.builder()
                    .id((Long) result[0])
                    .name((String) result[1])
                    .price((int) result[2])
                    .stockQuantity((int) result[3])
                    .dType((String) result[4])
                    .createdAt((Date) result[5])
                    .modifiedAt((Date) result[6])
                    .build();
            items.add(item);
        }
        return items;
    }
}
