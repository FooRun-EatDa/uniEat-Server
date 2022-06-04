package com.foorun.unieat.domain.bookmark.repository;

import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foorun.unieat.domain.bookmark.jpo.QBookmarkJpo.*;

@Repository
@RequiredArgsConstructor
public class BookmarkQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<RestaurantJpo> findBookmarkedRestaurantByMemberId(Long memberId){
        return jpaQueryFactory.select(bookmarkJpo.restaurant)
                .from(bookmarkJpo)
                .where(
                bookmarkJpo.member.id.eq(memberId)
                ).fetch();

    }

    public boolean isBookmarkedByMember(Long restaurantId,Long memberId){
        return jpaQueryFactory.select(bookmarkJpo.restaurant).from(bookmarkJpo).where(
                bookmarkJpo.member.id.eq(memberId).and(bookmarkJpo.restaurant.id.eq(restaurantId))
        ).fetchFirst() != null;
    }

    public BookmarkJpo findBookmarkByMemberIdAndRestaurantId(Long memberId, Long restaurantId){
        return jpaQueryFactory.selectFrom(bookmarkJpo).where(
                bookmarkJpo.member.id.eq(memberId),
                bookmarkJpo.restaurant.id.eq(restaurantId)
        ).fetchFirst();
    }
}
