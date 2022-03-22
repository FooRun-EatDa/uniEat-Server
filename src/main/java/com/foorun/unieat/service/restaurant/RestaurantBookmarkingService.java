package com.foorun.unieat.service.restaurant;

import com.foorun.unieat.domain.bookmark.dto.Bookmark;
import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import com.foorun.unieat.domain.bookmark.repository.BookmarkRepository;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantBookmarkingService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     *    식당 좋아요 누르기(찜한 맛집 추가)
     *    storeIdx : 찜할 식당 id
     *    memberUserDetails : 찜하는 유저 정보
     */
    public Long bookmarking(int storeIdx, MemberUserDetails memberUserDetails) {
        try {
            RestaurantJpo restaurant = restaurantRepository.findById((long)storeIdx).orElseThrow(NullPointerException::new);
            MemberJpo memberJpo = memberRepository.findById(memberUserDetails.getId()).orElseThrow(NullPointerException::new);
            Bookmark bookmark = Bookmark.of(restaurant,memberJpo);
            return bookmarkRepository.save(bookmark.asJpo()).getId();

        }catch (NullPointerException e){
            e.printStackTrace();
            throw new UniEatNotFoundException();
        }

    }

    /**
     * 찜한 맛집 리스트 조회
     */
    public List<RestaurantSimple> getBookmarkingList(MemberUserDetails memberUserDetails) {
        List<BookmarkJpo> bookmarkJpoList = bookmarkRepository.findBookmarkListByMemberId(memberUserDetails.getId()).orElseGet(null);
        List<RestaurantSimple> restaurantSimples = bookmarkJpoList.stream()
                .map(m-> RestaurantSimple.of(m.getRestaurant()))
                .collect(Collectors.toList());
        return restaurantSimples;

    }




}
