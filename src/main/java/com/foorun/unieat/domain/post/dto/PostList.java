package com.foorun.unieat.domain.post.dto;

import com.foorun.unieat.domain.post.jpo.PostJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostList {
    private Long id;
    private Long memberId;
    private String category;
    private String title;
    private String content;
    private String thumbnail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private int commentCount;

    /**
     * factory method
     * @return empty instance
     */
    public static PostList createEmpty() {
        return new PostList();
    }

    /**
     * factory method
     * @param postJpo JPA Object
     * @return properties copied instance
     */
    public static PostList of(PostJpo postJpo) {
        PostList post = createEmpty();
        BeanUtils.copyProperties(postJpo, post);
        post.setCommentCount(postJpo.getComments().size());
        return post;
    }
}
