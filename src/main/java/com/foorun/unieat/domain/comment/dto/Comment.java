package com.foorun.unieat.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.feeling.comment.dto.CommentFeeling;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.foorun.unieat.util.StreamUtil.map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private Long id;
    private Long postId;
    private Long memberId;
    private Long parentId;
    private boolean secret;
    private String content;
    private String status;

    @Builder.Default
    private List<CommentFeeling> feelings = new ArrayList<>();

    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    /**
     * factory method
     * @param commentJpo JPA Object
     * @return properties copied instance
     */
    public static Comment of(CommentJpo commentJpo) {
        Comment comment = new Comment();
        comment.postId = commentJpo.getPost().getId();
        if (commentJpo.hasParent()) {
            comment.parentId = commentJpo.getParent().getId();
        }
        if (commentJpo.hasChildren()) {
            comment.comments = commentJpo.getComments()
                    .stream()
                    .map(Comment::of)
                    .collect(Collectors.toList());
        }
        comment.feelings = map(commentJpo.getCommentFeelings(), CommentFeeling::of);
        BeanUtils.copyProperties(commentJpo, comment);
        return comment;
    }

    public CommentJpo asJpo() {
        CommentJpo commentJpo = new CommentJpo();
        BeanUtils.copyProperties(this, commentJpo);
        return commentJpo;
    }
}
