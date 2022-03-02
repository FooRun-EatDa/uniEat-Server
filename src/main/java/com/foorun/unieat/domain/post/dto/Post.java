package com.foorun.unieat.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.comment.dto.Comment;
import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.feeling.post.dto.PostFeeling;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.foorun.unieat.util.StreamUtil.map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post implements JsonSerializable {
    @Builder.Default
    private Long id = IdentifyGenerator.number();
    private Long memberId;
    private PostType category;
    private String title;
    private String content;
    private String thumbnail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private List<Comment> comments;
    private List<PostFeeling> feelings;

    /**
     * factory method
     * @return empty instance
     */
    public static Post createEmpty() {
        return new Post();
    }

    /**
     * factory method
     * @param postJpo JPA Object
     * @return properties copied instance
     */
    public static Post of(PostJpo postJpo) {
        Post post = createEmpty();
        BeanUtils.copyProperties(postJpo, post);
        post.comments = map(postJpo.getComments(),
                CommentJpo::isRoot,
                Comment::of);
        post.feelings = map(postJpo.getPostFeelings(),
                PostFeeling::of,
                Comparator.comparing(PostFeeling::getCreatedAt).reversed());
        return post;
    }

    public PostJpo asJpo() {
        PostJpo postJpo = new PostJpo();
        BeanUtils.copyProperties(this, postJpo);
        return postJpo;
    }
}
