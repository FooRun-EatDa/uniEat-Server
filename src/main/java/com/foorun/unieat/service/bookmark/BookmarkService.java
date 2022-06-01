package com.foorun.unieat.service.bookmark;

import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import com.foorun.unieat.domain.bookmark.repository.BookmarkQuerydslRepository;
import com.foorun.unieat.domain.bookmark.repository.BookmarkRepository;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkQuerydslRepository bookmarkQuerydslRepository;

    //맛집 북마크
    @Transactional
    public void bookmarking(Long storeId, MemberUserDetails memberUserDetails){
        Long memberId = memberUserDetails.getId();
        MemberJpo memberJpo = memberRepository.findById(memberId).orElseThrow(UniEatNotFoundException::new);
        RestaurantJpo restaurantJpo = restaurantRepository.findById(storeId).orElseThrow(UniEatNotFoundException::new);
        BookmarkJpo bookmarkJpo = BookmarkJpo.builder()
                .restaurant(restaurantJpo)
                .member(memberJpo)
                .build();

        bookmarkRepository.save(bookmarkJpo);

    }


    //유저가 북마크한 식당 리스트 조회
    @Transactional(readOnly = true)
    public List<Restaurant> getBookmarkedRestaurantList(MemberUserDetails memberUserDetails){
        return bookmarkQuerydslRepository.findBookmarkedRestaurantByMemberId(memberUserDetails.getId())
                        .stream()
                        .map(Restaurant::of)
                .collect(Collectors.toList());


    }
}
