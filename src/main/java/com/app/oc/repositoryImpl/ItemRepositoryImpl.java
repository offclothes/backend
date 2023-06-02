package com.app.oc.repositoryImpl;



import com.app.oc.dto.paging.SearchDto;
import com.app.oc.dto.shoppingmal.MainItemDto;
import com.app.oc.entity.Item;
import com.app.oc.repository.ItemRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.app.oc.entity.QItem.item;


public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    /**
     * 쇼핑몰 페이징
     *
     * @param shopItems
     * @param id        : Shopping Mal id
     * @param pageable
     * @return
     */

    @Override
    public Page<MainItemDto> shopMainItems(List<MainItemDto> shopItems, Long id, Pageable pageable) {

        JPAQuery<Item> total = getTotal(id);

        return PageableExecutionUtils.getPage(shopItems,pageable, total::fetchCount);


    }

    //List로 가져오기 - 원하는 페이지수까지
    @Override
    public List<Item> getcontent(Long id, Pageable pageable) {
        return queryFactory.selectFrom(item)
                .where(item.shoppingMal.shopId.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
    }

    //count
    private JPAQuery<Item> getTotal(Long id) {
        return queryFactory.selectFrom(item).where(item.shoppingMal.shopId.eq(id));
    }



    //카테고리 리스트
    @Override
    public List<Item> searchByCategory(Integer category, Pageable pageable) {
        return queryFactory.selectFrom(item)
                .where(item.category.eq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
    }

    @Override
    public List<Item> searchByKeyword(String keyword, Pageable pageable) {
        return queryFactory.selectFrom(item)
                .where(item.itemTitle.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
    }


    @Override
    public Page<SearchDto> pagingByCa(List<SearchDto> items, Integer category, Pageable pageable) {
        JPAQuery<Item> total = queryFactory.selectFrom(item).where(item.category.eq(category));

        return PageableExecutionUtils.getPage(items, pageable, total::fetchCount);
    }

    @Override
    public Page<SearchDto> pagingByKe(List<SearchDto> items, String keyword, Pageable pageable) {
        JPAQuery<Item> total = queryFactory.selectFrom(item).where(item.itemTitle.contains(keyword));

        return PageableExecutionUtils.getPage(items, pageable, total::fetchCount);

    }



}
