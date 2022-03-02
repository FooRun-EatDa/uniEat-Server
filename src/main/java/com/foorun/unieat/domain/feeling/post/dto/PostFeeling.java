package com.foorun.unieat.domain.feeling.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo;
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
public class PostFeeling implements JsonSerializable {
    private Long id;
    private Long postId;
    private Member member;
    private LocalDateTime createdAt;

    /**
     * factory method
     * @return empty instance
     */
    public static PostFeeling createEmpty() {
        return new PostFeeling();
    }

    /**
     * factory method
     */
    public static PostFeeling of(PostFeelingJpo postFeelingJpo) {
        PostFeeling post = createEmpty();
        post.id = postFeelingJpo.getId();
        post.postId = postFeelingJpo.getPost().getId();
        post.member = Member.of(postFeelingJpo.getMember());
        post.createdAt = postFeelingJpo.getCreatedAt();
        return post;
    }

    public PostFeelingJpo asJpo() {
        PostFeelingJpo postFeelingJpo = new PostFeelingJpo();
        BeanUtils.copyProperties(this, postFeelingJpo);
        return postFeelingJpo;
    }
}
