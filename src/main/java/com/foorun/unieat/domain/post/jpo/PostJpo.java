package com.foorun.unieat.domain.post.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.common.StatusType;
import com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "post")
public class PostJpo extends BaseTimeJpo {
    @Id
    @Column(name = "post_id")
    private Long id;

    /**
     * 게시글 카테고리
     */
    @Enumerated(EnumType.ORDINAL)
    private PostType category;

    /**
     * 제목
     */
    private String title;

    /**
     * 내용
     */
    private String content;

    /**
     * 대표 이미지
     */
    private String thumbnail;

    /**
     * 게시글 상태
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.ACTIVE;

    @OneToMany
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private Set<CommentJpo> comments = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    @Builder.Default
    private MemberJpo member = new MemberJpo();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "post")
    private Set<PostFeelingJpo> postFeelings = new LinkedHashSet<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "post")
    private Set<PostFileJpo> files = new LinkedHashSet<>();

    /**
     * 댓글(Comment) 객체 Dirty Checking
     * @param commentJpo 댓글 JPA Object
     */
    public void addComment(CommentJpo commentJpo) {
        this.comments.add(commentJpo);
    }

    /**
     * 게시글을 삭제됨 상태로 처리 Dirty Checking
     */
    public void remove() {
        this.status = StatusType.REMOVED;
    }
}
