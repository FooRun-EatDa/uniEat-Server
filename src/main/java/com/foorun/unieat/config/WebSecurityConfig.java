package com.foorun.unieat.config;

import com.foorun.unieat.config.filter.JwtAuthenticationFilter;
import com.foorun.unieat.config.handler.JwtAccessDeniedHandler;
import com.foorun.unieat.config.handler.JwtAuthenticationEntryPoint;
import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .formLogin().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 x
                .and()
                .authorizeRequests()
                .antMatchers("/member/sign-*/**",
                        "/swagger*/**",
                        "/school/**",
                        "/webjars/**",
                        "/member/verify-email",
                        "/member/reset-password",
                        "/member/token/re-issue",
                        "/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider, memberRepository), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutSuccessUrl("/"); //로그아웃시 이동할 url
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader(JwtConstant.HEADER_NAME);
        configuration.addExposedHeader(JwtConstant.HEADER_NAME_REFRESH_TOKEN);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs/**",
                "/swagger-ui/**",
                "/member/sign-*/**",
                "/school/**",
                "/member/verify-email",
                "/member/reset-password",
                "/member/token/re-issue",
                "/swagger-ui.html");
    }
}
