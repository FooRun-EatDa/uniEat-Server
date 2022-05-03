package com.foorun.unieat.service.review.validChecker;

import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.review.dto.ReviewReq;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ReviewValidCheck {


    //리뷰 업데이트 체크
   public boolean updateValidCheck(ReviewJpo reviewJpo , MemberUserDetails member){
        Long writerId = reviewJpo.getMember().getId();
        Long memberId = member.getId();
        if(writerId != memberId)return false;
        else return true;
    }





}
