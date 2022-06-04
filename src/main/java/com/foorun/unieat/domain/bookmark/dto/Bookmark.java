package com.foorun.unieat.domain.bookmark.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bookmark {

    @Builder.Default
    private Long id = IdentifyGenerator.number();
    private Restaurant restaurant;
    private Member member;


    public static Bookmark of(RestaurantJpo restaurantJpo, MemberJpo memberJpo){
        Bookmark bookmark = new Bookmark();
        bookmark.restaurant = Restaurant.of(restaurantJpo);
        bookmark.member = Member.of(memberJpo);
        return bookmark;

    }



    public BookmarkJpo asJpo(){
        BookmarkJpo bookmarkJpo = new BookmarkJpo();
        BeanUtils.copyProperties(this,bookmarkJpo);
        bookmarkJpo.setRestaurant(this.restaurant.asJpo());
        bookmarkJpo.setMember(this.member.asJpo());
        return bookmarkJpo;

    }


}
