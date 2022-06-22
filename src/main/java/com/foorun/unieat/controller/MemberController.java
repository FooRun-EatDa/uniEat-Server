package com.foorun.unieat.controller;

import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.member.dto.*;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingNicknamePayload;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingNotificationPayload;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingPasswordPayload;
import com.foorun.unieat.domain.member.dto.setting.MemberSettingProfilePayload;
import com.foorun.unieat.service.member.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.foorun.unieat.constant.JwtConstant.HEADER_NAME_REFRESH_TOKEN;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.MEMBER)
@CrossOrigin(allowCredentials = "true", originPatterns = "*", exposedHeaders = {JwtConstant.HEADER_NAME, HEADER_NAME_REFRESH_TOKEN})
public class MemberController {
    private final MemberSignUpService memberSignUpService;
    private final MemberSignInService memberSignInService;
    private final MemberResetPasswordService memberResetPasswordService;
    private final MemberVerifyEmailService memberVerifyEmailService;
    private final MemberSettingService memberSettingService;
    private final MemberSignOutService memberSignOutService;
    private final MemberTokenService memberTokenService;

    @ApiOperation(value = SwaggerApiInfo.SIGN_IN)
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<Void>> signIn(@RequestBody MemberSignIn memberSignIn) {
        JwtToken jwtToken = memberSignInService.signIn(memberSignIn);
        return ResponseEntity.ok()
                .headers(HttpHeaders.readOnlyHttpHeaders(jwtToken.asHeaders()))
                .body(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP)
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody MemberSignUp memberSignUp) {
        memberSignUpService.signUp(memberSignUp);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiImplicitParam(name = "email", required = true, value = "사용자가 입력한 이메일", example = "cha2.hoon@gmail.com", dataTypeClass = String.class)
    @ApiOperation(value = SwaggerApiInfo.SIGN_UP_CHECK_EMAIL)
    @GetMapping("/sign-up/check-email")
    public ResponseEntity<ApiResponse<Boolean>> signUpCheckEmail(
            @RequestParam("email") String email) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(memberSignUpService.isDuplicateEmail(email)));
    }

    @ApiImplicitParam(name = "nickname", required = true, value = "사용자가 입력한 닉네임", example = "chaehoon", dataTypeClass = String.class)
    @ApiOperation(value = SwaggerApiInfo.SIGN_UP_CHECK_NICKNAME)
    @GetMapping("/sign-up/check-nickname")
    public ResponseEntity<ApiResponse<Boolean>> signUpCheckNickname(
            @RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(memberSignUpService.isDuplicateNickname(nickname)));
    }

    @ApiOperation(value = SwaggerApiInfo.GET_VERIFY_EMAIL)
    @GetMapping("/verify-email")
    public ResponseEntity<ApiResponse<Boolean>> verifyEmail(
            @ModelAttribute MemberVerifyEmailPayload memberVerifyEmailPayload) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(memberVerifyEmailService.verify(memberVerifyEmailPayload)));
    }

    @ApiOperation(value = SwaggerApiInfo.POST_VERIFY_EMAIL)
    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponse<Void>> verifyEmail(
            @RequestBody MemberSendVerifyEmailPayload memberSendVerifyEmailPayload) {
        memberVerifyEmailService.send(memberSendVerifyEmailPayload);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.RESET_PASSWORD)
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(
            @RequestBody MemberResetPasswordPayload memberResetPasswordPayload) {
        memberResetPasswordService.resetPassword(memberResetPasswordPayload);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SETTING_NICKNAME)
    @PutMapping("/setting-nickname")
    public ResponseEntity<ApiResponse<Void>> settingNickname(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestBody @Validated MemberSettingNicknamePayload memberSettingNicknamePayload) {
        memberSettingService.nickname(memberUserDetails, memberSettingNicknamePayload);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SETTING_PROFILE)
    @PutMapping("/setting-profile")
    public ResponseEntity<ApiResponse<Void>> settingProfile(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestBody @Validated MemberSettingProfilePayload memberSettingProfilePayload) {
        memberSettingService.profile(memberUserDetails, memberSettingProfilePayload);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SETTING_NOTIFICATION)
    @PutMapping("/setting-notification")
    public ResponseEntity<ApiResponse<Void>> settingNotification(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestBody MemberSettingNotificationPayload memberSettingNotificationPayload) {
        memberSettingService.notification(memberUserDetails, memberSettingNotificationPayload);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SETTING_PASSWORD)
    @PutMapping("/setting-password")
    public ResponseEntity<ApiResponse<Void>> settingPassword(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestBody MemberSettingPasswordPayload memberSettingPasswordPayload) {
        memberSettingService.password(memberUserDetails, memberSettingPasswordPayload);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_OUT)
    @PostMapping("/sign-out")
    public ResponseEntity<ApiResponse<Void>> signOut(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails) {
        memberSignOutService.signOut(memberUserDetails);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.WITHDRAW)
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<Void>> withdraw(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails) {
        memberSettingService.withdraw(memberUserDetails);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.RE_ISSUE_TOKEN)
    @PostMapping("/token/re-issue")
    public ResponseEntity<ApiResponse<Void>> reIssue(
            @RequestHeader(HEADER_NAME_REFRESH_TOKEN) String refreshToken) {
        JwtToken jwtToken = memberTokenService.reIssueToken(refreshToken);
        return ResponseEntity.ok()
                .headers(HttpHeaders.readOnlyHttpHeaders(jwtToken.asHeaders()))
                .body(ApiResponse.success());
    }
}
