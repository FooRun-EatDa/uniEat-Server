package com.foorun.unieat.service.bookmark;

import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import com.foorun.unieat.domain.bookmark.respository.BookmarkQuerydslRepository;
import com.foorun.unieat.domain.bookmark.respository.BookmarkRepository;
import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookmarkTest extends ServiceTest {


    @Mock
    private BookmarkRepository bookmarkRepository;

    @Mock
    private BookmarkQuerydslRepository bookmarkQuerydslRepository;

    @InjectMocks
    private BookmarkService bookmarkService;


    @DisplayName("식당 북마킹 서비스 테스트 성공")
    @Test
    void When_MemberUserDetails_Expect_Bookmarking_Success() {

        Long storeId = 1L;
        when(memberUserDetails.getId()).thenReturn(1L);
        when(memberRepository.findById(any(Long.class))).thenReturn(Optional.of(mock(MemberJpo.class)));
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(mock(RestaurantJpo.class)));
        when(bookmarkRepository.save(any())).then(I -> {
                    return I.getArgument(0);
                }
        );

        bookmarkService.bookmarking(storeId, memberUserDetails);

        verify(bookmarkRepository).save(any(BookmarkJpo.class));


    }


    @DisplayName("북마크(좋아요) 한 식당 목록 조회")
    @Test
    void When_ReadBookmarking_With_MemberUserDetail_Than_getBookmarkedList(){
        List<RestaurantJpo> restaurantJpoList = new ArrayList<>();
        when(memberUserDetails.getId()).thenReturn(1L);
        when(bookmarkQuerydslRepository.findBookmarkedRestaurantByMemberId(any(Long.class))).thenReturn(restaurantJpoList);
        bookmarkService.getBookmarkedRestaurantList(memberUserDetails);

        verify(memberUserDetails).getId();
        verify(bookmarkQuerydslRepository).findBookmarkedRestaurantByMemberId(any(Long.class));


    }
}
