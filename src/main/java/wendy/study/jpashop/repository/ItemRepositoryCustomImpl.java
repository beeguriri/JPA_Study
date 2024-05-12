package wendy.study.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import wendy.study.jpashop.dto.ItemDto;
import wendy.study.jpashop.model.Item;
import wendy.study.jpashop.params.ItemSearchParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static wendy.study.jpashop.model.QItem.item;

@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ItemRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

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

    @Override
    public Page<Item> searchItemsWithPage(ItemSearchParam param, Pageable pageable) {

        List<Item> items = queryFactory.selectFrom(item)
                .where(searchByItemName(param.getName()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(item.count())
                .from(item)
                .where(searchByItemName(param.getName()));

        return PageableExecutionUtils.getPage(items, pageable, countQuery::fetchOne);
    }

    private BooleanExpression searchByItemName(String param) {
        return StringUtils.hasText(param) ? item.name.containsIgnoreCase(param) : null;
    }
}
