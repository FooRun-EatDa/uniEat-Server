package com.foorun.unieat.domain.member.dto;

import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberUserDetails implements UserDetails {
    private Long id;
    private String email;
    private String nickname;
    private Role role;

    public static MemberUserDetails of(MemberJpo memberJpo) {
        return of(memberJpo.getId(), memberJpo.getEmail(), memberJpo.getNickname(), memberJpo.getRole());
    }

    public static MemberUserDetails of(Long id, String email, String nickname, Role role) {
        return MemberUserDetails.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .role(role)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getKey()));
    }

    public String getEmail(){return this.email;}

    public String getNickname(){return this.nickname;}

    public Long getId(){return this.id;}


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return Long.toString(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
