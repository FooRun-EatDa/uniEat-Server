package com.foorun.unieat.domain.comment.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "comment")
public class CommentJpo extends BaseTimeJpo {
    @Id
    @Column(name = "comment_id")
    private Long id;

    /**
     * 부모 댓글 고유 ID
     */
    private Long parentId;

    /**
     * 비밀글 여부
     */
    @Column(name = "is_secret")
    private boolean secret;

    /**
     * 내용
     */
    private String content;

    /**
     * 댓글 상태
     */
    private String status;

    /**
     * 댓글 연관 게시글
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private PostJpo post;
}
