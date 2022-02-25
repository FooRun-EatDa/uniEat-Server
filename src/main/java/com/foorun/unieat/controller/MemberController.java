package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.member.dto.*;
import com.foorun.unieat.service.member.MemberResetPasswordService;
import com.foorun.unieat.service.member.MemberSignInService;
import com.foorun.unieat.service.member.MemberSignUpService;
import com.foorun.unieat.service.member.MemberVerifyEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.MEMBER)
public class MemberController {
    private final MemberSignUpService memberSignUpService;
    private final MemberSignInService memberSignInService;
    private final MemberResetPasswordService memberResetPasswordService;
    private final MemberVerifyEmailService memberVerifyEmailService;

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

    @ApiImplicitParam(name = "email", required = true, value = "사용자가 입력한 이메일", example = "cha2.hoon@gmail.com")
    @ApiOperation(value = SwaggerApiInfo.SIGN_UP_CHECK_EMAIL)
    @GetMapping("/sign-up/check-email")
    public ResponseEntity<ApiResponse<Boolean>> signUpCheckEmail(
            @RequestParam("email") String email) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(memberSignUpService.isDuplicateEmail(email)));
    }

    @ApiImplicitParam(name = "nickname", required = true, value = "사용자가 입력한 닉네임", example = "chaehoon")
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
}
