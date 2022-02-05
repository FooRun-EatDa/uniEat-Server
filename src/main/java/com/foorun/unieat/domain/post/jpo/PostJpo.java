package com.foorun.unieat.domain.post.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String category;

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
    private String status;

    @OneToMany
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private List<CommentJpo> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private MemberJpo member = new MemberJpo();
}
