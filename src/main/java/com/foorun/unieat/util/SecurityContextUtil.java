package com.foorun.unieat.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUtil {
    /**
     * {@link SecurityContextHolder} context 에 존재하는 사용자 ID
     * @return memberId
     */
    public Long getAuthenticationMemberId(UserDetails userDetails) {
        return Long.parseLong(userDetails.getUsername());
    }
}
