package com.foorun.unieat.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Member implements JsonSerializable {
    private Long id;
    private String schoolId;
    private String email;
    private String profile;
    private String nickname;
    private Integer dateOfBirth;
    private int gender;
    private int grade;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String name;

    @JsonIgnore
    private String status;

    /**
     * factory method
     * @return empty instance
     */
    public static Member createEmpty() {
        return new Member();
    }

    /**
     * factory method
     * @param memberJpo JPA Object
     * @return properties copied instance
     */
    public static Member of(MemberJpo memberJpo) {
        Member member = createEmpty();
        BeanUtils.copyProperties(memberJpo, member);
        return member;
    }

    public MemberJpo asJpo() {
        MemberJpo memberJpo = new MemberJpo();
        BeanUtils.copyProperties(this, memberJpo);
        return memberJpo;
    }
}
