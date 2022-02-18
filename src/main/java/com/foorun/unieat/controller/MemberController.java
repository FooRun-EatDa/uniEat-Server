package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.member.dto.MemberSignIn;
import com.foorun.unieat.domain.member.dto.MemberSignUp;
import com.foorun.unieat.service.member.MemberSignInService;
import com.foorun.unieat.service.member.MemberSignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.MEMBER)
public class MemberController {
    private final MemberSignUpService memberSignUpService;
    private final MemberSignInService memberSignInService;

    @ApiOperation(value = SwaggerApiInfo.SIGN_IN)
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<Void>> signIn(@RequestBody MemberSignIn memberSignIn) {
        memberSignInService.signIn(memberSignIn);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP)
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody MemberSignUp memberSignUp) {
        memberSignUpService.signUp(memberSignUp);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP_CHECK_EMAIL)
    @GetMapping("/sign-up/check-email")
    public ResponseEntity<ApiResponse<Boolean>> signUpCheckEmail(
            @ApiParam(required = true, name = "사용자가 입력한 이메일") @RequestParam("email") String email) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(memberSignUpService.isDuplicateEmail(email)));
    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP_CHECK_NICKNAME)
    @GetMapping("/sign-up/check-nickname")
    public ResponseEntity<ApiResponse<Boolean>> signUpCheckNickname(
            @ApiParam(required = true, name = "사용자가 입력한 닉네임") @RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(memberSignUpService.isDuplicateNickname(nickname)));
    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP_VERIFY_EMAIL)
    @GetMapping("/sign-up/verify-email")
    public ResponseEntity<ApiResponse<Boolean>> signUpVerifyEmail(
            @ApiParam(required = true, name = "사용자가 입력한 이메일") @RequestParam("email") String email,
            @ApiParam(required = true, name = "사용자가 입력한 인증코드") @RequestParam("verificationCode") String verificationCode) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(memberSignUpService.verifyEmail(email, verificationCode)));
    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP_SEND_VERIFICATION_EMAIL)
    @GetMapping("/sign-up/send-verification-email")
    public ResponseEntity<ApiResponse<Void>> signUpSendVerificationEmail(
            @ApiParam(required = true, name = "사용자가 입력한 이메일") @RequestParam("email") String email) {
        memberSignUpService.sendVerificationEmail(email);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
