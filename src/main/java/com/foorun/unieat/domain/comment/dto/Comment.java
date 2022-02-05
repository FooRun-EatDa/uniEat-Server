package com.foorun.unieat.domain.comment.dto;

import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id;
    private Long postId;
    private Long memberId;
    private Long parentId;
    private boolean secret;
    private String content;
    private String status;

    /**
     * factory method
     * @param commentJpo JPA Object
     * @return properties copied instance
     */
    public static Comment of(CommentJpo commentJpo) {
        Comment comment = new Comment();
        comment.postId = commentJpo.getPost().getId();
        BeanUtils.copyProperties(commentJpo, comment);
        return comment;
    }
}
