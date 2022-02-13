package com.foorun.unieat.domain.member.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.school.jpo.SchoolJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "member")
public class MemberJpo extends BaseTimeJpo {
    @Id
    @Column(name = "member_id", columnDefinition = "회원 고유 번호")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "school_id", columnDefinition = "학교 고유 ID")
    private SchoolJpo school;

    @Column(columnDefinition = "회원 Email 주소")
    private String email;

    @Column(columnDefinition = "사용자 프로필 이미지")
    private String profile;

    @Column(columnDefinition = "암호화 된 이용자 패스워드")
    private String password;

    @Column(columnDefinition = "회원 이름(최대 4글자)")
    private String name;

    @Column(columnDefinition = "회원 별명(최대 5글자)")
    private String nickname;

    @Column(columnDefinition = "회원 생년월일 (주민번호 앞 7자리)")
    private Integer dateOfBirth;

    @Column(columnDefinition = "회원 성별(0 = 남자, 1 = 여자)")
    private int gender;

    @Column(columnDefinition = "이용자 권한(0 = 관리자, 1 = 일반 이용자, 2 = Black 회원)")
    private int grade;

    @Column(columnDefinition = "회원 상태")
    private String status;
}
