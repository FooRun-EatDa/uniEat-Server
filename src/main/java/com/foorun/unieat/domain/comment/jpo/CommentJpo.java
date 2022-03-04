package com.foorun.unieat.domain.comment.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.feeling.comment.jpo.CommentFeelingJpo;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.exception.UniEatLogicalException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CommentJpo parent;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    @Builder.Default
    private Set<CommentJpo> comments = new HashSet<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "comment")
    private List<CommentFeelingJpo> commentFeelings = new ArrayList<>();

    /**
     * 최상위 댓글 여부 확인
     * @return 최상위 댓글인가 ?
     */
    public boolean isRoot() {
        return isNull(parent);
    }

    /**
     * 상위 댓글 존재 여부 확인
     * @return 상위 댓글이 존재하는가 ?
     */
    public boolean hasParent() {
        return nonNull(parent);
    }

    /**
     * 하위 댓글 존재 여부 확인
     * @return 하위 댓글이 존재하는가 ?
     */
    public boolean hasChildren() {
        return !comments.isEmpty();
    }

    /**
     * 하위 댓글 추가
     * @throws UniEatLogicalException 현재 버전까지는 하위 1 depth 까지만 허용함으로 로직상 문제가 발생할 경우 예외 발생
     */
    public void addComment(CommentJpo commentJpo) {
        if (!isRoot()) {
            throw new UniEatLogicalException();
        }
        this.comments.add(commentJpo);
    }
}
