package com.foorun.unieat.domain.member.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import com.foorun.unieat.domain.common.StatusType;
import com.foorun.unieat.domain.feeling.comment.jpo.CommentFeelingJpo;
import com.foorun.unieat.domain.feeling.jpo.RestaurantFeelingJpo;
import com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo;
import com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo;
import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.school.jpo.SchoolJpo;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "member")
@DynamicUpdate
public class MemberJpo extends BaseTimeJpo implements Persistable<Long> {
    /**
     * 회원 고유 번호
     */
    @Id
    @Column(name = "member_id")
    private Long id;

    /**
     * 학교 고유 ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "school_id")
    private SchoolJpo school;

    /**
     * 좋아요한 리뷰 리스트
     */
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    @Builder.Default
    private Set<ReviewFeelingJpo> reviewFeelings = new HashSet<>();

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    @Builder.Default
    private Set<RestaurantFeelingJpo> restaurantFeelings = new HashSet<>();


    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    @Builder.Default
    private Set<PostFeelingJpo> postFeelings = new HashSet<>();


    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    @Builder.Default
    private Set<CommentFeelingJpo> commentFeelings = new HashSet<>();



    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    @Builder.Default
    private Set<BookmarkJpo> bookmarks = new HashSet<>();


    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    @Builder.Default
    private Set<SearchLogJpo> searchLogs = new HashSet<>();



    /**
     * 회원 Email 주소
     */
    private String email;

    /**
     * 사용자 프로필 이미지
     */
    private String profile;

    /**
     * 암호화 된 이용자 패스워드
     */
    private String password;

    /**
     * 회원 이름(최대 4글자)
     */
    private String name;

    /**
     * 회원 별명(최대 5글자)
     */
    private String nickname;

    /**
     * 회원 생년월일 (주민번호 앞 6자리)
     */
    private Integer dateOfBirth;    //  TODO : Maybe value is started 0 ..?

    /**
     * 회원 성별(0 = 남자, 1 = 여자)
     */
    private char gender;

    /**
     * 이용자 권한(0 = 관리자, 1 = 일반 이용자, 2 = Black 회원)
     */
    private int grade;

    /**
     * 회원 상태
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.ACTIVE;

    /**
     * 최근 로그인 일시
     */
    private LocalDateTime latestSignInAt;

    /**
     * 개인정보 수집 동의 여부
     */
    private boolean agreeTerms;

    /**
     * 이벤트 수신 동의 여부
     */
    private boolean agreeEventLetter;

    /**
     * 알림 수신 활성 여부
     */
    private boolean agreeNotification;

    /**
     * 사용자 권한
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * 비밀번호 변경
     */
    public void changePassword(String password) {
        this.password = password;
    }

    /**
     * 최근 로그인 일시 현재 시간 기준으로 갱신
     */
    public void latestSignInNow() {
        this.latestSignInAt = LocalDateTime.now();
    }

    /**
     * 프로필 이미지 변경
     * @param profile file 고유 ID
     */
    public void changeProfile(String profile) {
        this.profile = profile;
    }

    /**
     * 사용자 닉네임 변경
     * @param nickname 별명
     */
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 탈퇴 처리 (삭제 상태로 변경)
     */
    public void withdraw() {
        this.status = StatusType.REMOVED;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public boolean equals(Long id) {
        return this.id.equals(id);
    }

    public MemberUserDetails asUserDetails() {
        return MemberUserDetails.of(this);
    }
}
