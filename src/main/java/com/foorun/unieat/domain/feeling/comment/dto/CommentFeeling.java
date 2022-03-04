package com.foorun.unieat.domain.feeling.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.feeling.comment.jpo.CommentFeelingJpo;
import com.foorun.unieat.domain.member.dto.Member;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentFeeling implements JsonSerializable {
    private Long commentId;
    private Member member;
    private LocalDateTime createdAt;

    /**
     * factory method
     * @return empty instance
     */
    public static CommentFeeling createEmpty() {
        return new CommentFeeling();
    }

    /**
     * factory method
     */
    public static CommentFeeling of(CommentFeelingJpo commentFeelingJpo) {
        CommentFeeling commentFeeling = createEmpty();
        commentFeeling.commentId = commentFeelingJpo.getComment().getId();
        commentFeeling.member = Member.of(commentFeelingJpo.getMember());
        commentFeeling.createdAt = commentFeelingJpo.getCreatedAt();
        return commentFeeling;
    }

    public CommentFeelingJpo asJpo() {
        CommentFeelingJpo commentFeelingJpo = new CommentFeelingJpo();
        BeanUtils.copyProperties(this, commentFeelingJpo);
        return commentFeelingJpo;
    }
}
