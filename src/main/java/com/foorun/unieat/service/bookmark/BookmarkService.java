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
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 식당 좋아요 서비스
 */
@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkQuerydslRepository bookmarkQuerydslRepository;


    /**
     *    식당 좋아요 누르기(찜한 맛집 추가)
     *    BookmarkingReq: 찜할 식당 목록과
     *    memberUserDetails : 찜하는 유저 정보
     */
    //맛집 북마크
    @Transactional
    public void bookmarking(List<Long> bookmarkingList, MemberUserDetails memberUserDetails){

        Long memberId = memberUserDetails.getId();
        MemberJpo memberJpo = memberRepository.findById(memberId).orElseThrow(UniEatNotFoundException::new);
        List<RestaurantJpo> restaurants =  findRestaurantsByList(bookmarkingList);
        memberBookmarkingByRestaurantList(memberJpo, restaurants);

    }


    private void memberBookmarkingByRestaurantList(MemberJpo memberJpo, List<RestaurantJpo> restaurants) {
        for (RestaurantJpo restaurant : restaurants) {
            BookmarkJpo bookmarkJpo = BookmarkJpo.builder()
                    .id(IdentifyGenerator.number())
                    .restaurant(restaurant)
                    .member(memberJpo)
                    .build();
            bookmarkRepository.save(bookmarkJpo);
        }
    }


    //맛집 북마크 취소
    @Transactional
    public void bookmarkCancel(List<Long> bookmarkingList, MemberUserDetails memberUserDetails){
        Long memberId = memberUserDetails.getId();
        List<RestaurantJpo> forCancelRestaurant = findRestaurantsByList(bookmarkingList);

        bookmarkCacelingByList(memberId, forCancelRestaurant);

    }



    private void bookmarkCacelingByList(Long memberId, List<RestaurantJpo> forCancelRestaurant) {
        for (RestaurantJpo restaurantJpo : forCancelRestaurant) {
            BookmarkJpo bookmarkJpo = bookmarkQuerydslRepository
                    .findBookmarkByMemberIdAndRestaurantId(memberId, restaurantJpo.getId())
                    .orElseThrow(UniEatNotFoundException::new);
            bookmarkRepository.delete(bookmarkJpo);
        }
    }



    private List<RestaurantJpo> findRestaurantsByList(List<Long> bookmarkingList) {
        List<RestaurantJpo> list = new ArrayList<>();
        for (Long storeId : bookmarkingList) {
            list.add(restaurantRepository.findById(storeId).orElseThrow(UniEatNotFoundException::new));
        }
        return list;
    }


    //유저가 북마크한 식당 리스트 조회
    @Transactional(readOnly = true)
    public List<Restaurant> getBookmarkedRestaurantList(MemberUserDetails memberUserDetails){
        return bookmarkQuerydslRepository.findBookmarkedRestaurantByMemberId(memberUserDetails.getId())
                        .stream()
                        .map(Restaurant::of).map(r-> {
                    r.setLiked(true);
                    return r;
                }).collect(Collectors.toList());


    }
}
