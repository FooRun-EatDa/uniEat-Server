package com.foorun.unieat.domain.bookmark.respository;

import com.foorun.unieat.domain.bookmark.jpo.QBookmarkJpo;
import com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.bookmark.jpo.QBookmarkJpo.*;
import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo.*;

@Repository
@RequiredArgsConstructor
public class BookmarkQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<RestaurantJpo> findBookmarkedRestaurantByMemberId(Long memberId){
        return jpaQueryFactory.select(bookmarkJpo.restaurant).from(bookmarkJpo).where(
                bookmarkJpo.member.id.eq(memberId)).fetch();

    }
}
