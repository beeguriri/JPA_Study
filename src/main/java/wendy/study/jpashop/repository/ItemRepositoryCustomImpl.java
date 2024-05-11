package wendy.study.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import wendy.study.jpashop.dto.ItemDto;
import wendy.study.jpashop.params.ItemSearchParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<ItemDto> searchAllItems(ItemSearchParam param) {

        StringBuilder queryBuilder = new StringBuilder("SELECT ITEM_ID, NAME, PRICE, STOCK_QUANTITY, DTYPE, CREATED_DATE, MODIFIED_DATE FROM ITEM");

        // 검색 조건이 존재할 때 WHERE 절 추가
        boolean existName = false;
        if (StringUtils.hasText(param.getName())) {
            queryBuilder.append(" WHERE NAME LIKE :name");
            existName = true;
        }

        if (param.getDType() != null) {
            if (existName) {
                queryBuilder.append(" AND");
            } else {
                queryBuilder.append(" WHERE");
                existName = true;
            }
            queryBuilder.append(" DTYPE = :dType");
        }

        // EntityManager를 사용하여 네이티브 쿼리 실행
        Query nativeQuery = entityManager.createNativeQuery(queryBuilder.toString());

        // 매개변수 바인딩
        if (StringUtils.hasText(param.getName())) {
            nativeQuery.setParameter("name", "%" + param.getName() + "%");
        }
        if (param.getDType() != null) {
            nativeQuery.setParameter("dType", param.getDType().toString());
        }

        List<Object[]> results = nativeQuery.getResultList();

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
