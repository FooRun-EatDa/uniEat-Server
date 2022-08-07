package com.foorun.unieat.controller;

import com.foorun.unieat.constant.EmailVerifyType;
import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.member.dto.MemberSendVerifyEmailPayload;
import com.foorun.unieat.domain.member.dto.MemberVerifyEmailPayload;
import com.foorun.unieat.service.member.MemberVerifyEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.foorun.unieat.constant.JwtConstant.HEADER_NAME_REFRESH_TOKEN;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.AUTH)
@CrossOrigin(allowCredentials = "true", originPatterns = "*", exposedHeaders = {JwtConstant.HEADER_NAME, HEADER_NAME_REFRESH_TOKEN})
public class AuthController {
    private final MemberVerifyEmailService memberVerifyEmailService;

    @ApiImplicitParam(name = "email", required = true, value = "사용자 이메일", example = "cha2.hoon@gmail.com", dataTypeClass = String.class)
    @ApiOperation(value = SwaggerApiInfo.VERIFY_EMAIL)
    @GetMapping
    public ResponseEntity<ApiResponse<Long>> post(
            @RequestParam String email) {
        MemberSendVerifyEmailPayload payload = new MemberSendVerifyEmailPayload(email, EmailVerifyType.SIGN_UP);
        return ResponseEntity.ok(ApiResponse
                .valueOf(memberVerifyEmailService.send(payload)));
    }

    @PostMapping
    @ApiOperation(value = SwaggerApiInfo.VERIFY_EMAIL_SIGN_UP)
    public ResponseEntity<ApiResponse<Long>> get(MemberVerifyEmailPayload payload) {
        return ResponseEntity.ok(ApiResponse
                .valueOf(memberVerifyEmailService.verifyAndSignup(payload)));
    }
}
