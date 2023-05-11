package com.app.oc.repositoryImpl;

import com.app.oc.dto.paging.SearchDto;
import com.app.oc.dto.shoppingmal.MainItemDto;
import com.app.oc.entity.Item;
import com.app.oc.entity.QFile;
import com.app.oc.repository.ItemRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    @Override
    public Page<SearchDto> searchPageItemM(Pageable pageable) {
        QueryResults<SearchDto> results = queryFactory
                .select(new QSearchDto(
                        item.itemId,
                        item.itemTitle,
                        item.category,
                        item.file
                        )
                )
                .from(item)
                .join(item.files,file)
                .where(item.category.eq(0).a)
                .orderBy(item.itemId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<SearchDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<SearchDto> searchPageItemF(Pageable pageable) {
        QueryResults<SearchDto> results = queryFactory
                .select(new QSearchDto(
                        item.itemId,
                        item.itemTitle,
                        item.category,
                        item.file
                ))
                .from(item)
                .join(item.files,file)
                .where(item.category.eq(1)).and(QFile.filename.contains("s_"))
                .orderBy(item.itemId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<SearchDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<SearchDto> searchPageItemB(Pageable pageable) {
        QueryResults<SearchDto> results = queryFactory
                .select(new QSearchDto(
                        item.itemId,
                        item.itemTitle,
                        item.category,
                        item.file
                ))
                .from(item)
                .join(item.files,file)
                .where(item.category.eq(2)).and(QFile.filename.contains("s_"))
                .orderBy(item.itemId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<SearchDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<SearchDto> findByKeyword(String keyword, Pageable pageable) {
        QueryResults<SearchDto> results = queryFactory
                .select(new QSearchDto(
                        item.itemId,
                        item.itemTitle,
                        item.category,
                        item.file
                ))
                .from(item)
                .join(item.files,file)
                .where(keywordEq(keyword)).and(QFile.filename.contains("s_"))
                .orderBy(item.itemId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<SearchDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private Predicate keywordEq(String keyword){
        return keyword != null ? item.itemTitle.contains(keyword) : null;
    }

}
