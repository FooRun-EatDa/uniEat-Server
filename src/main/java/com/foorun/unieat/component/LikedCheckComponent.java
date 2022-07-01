package com.foorun.unieat.component;

import com.foorun.unieat.component.Likable;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * T 는 JPO가 아닌 응답 DTO 타입이어야함
 * 추후 리팩터링 필요
 */
@Deprecated
public final class LikedCheckComponent {

    private MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public <T extends  Likable,R> T addIsLiked(T likable, Boolean isOkToAdd){
        if(userDetails != null){
            if(isOkToAdd){
                likable.setIsLiked(true);
            }
            else{
                likable.setIsLiked(false);
            }
            return likable;
        }
        else {
            likable.setIsLiked(false);
            return likable;
        }

    }
}
