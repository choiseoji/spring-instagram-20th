package com.ceos20.instagram.auth.controller;

import com.ceos20.instagram.auth.dto.SignInRequest;
import com.ceos20.instagram.auth.dto.SignUpRequest;
import com.ceos20.instagram.auth.service.AuthService;
import com.ceos20.instagram.common.response.ApiResponse;
import com.ceos20.instagram.common.response.ResponseBuilder;
import com.ceos20.instagram.global.jwt.JwtTokenProvider;
import com.ceos20.instagram.global.jwt.dto.JwtToken;
import com.ceos20.instagram.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signUp")
    private ResponseEntity<ApiResponse<Void>> signUp(@RequestBody SignUpRequest signUpRequest) {

        authService.signUp(signUpRequest);
        return ResponseBuilder.createApiResponse("회원가입 성공", null);
    }

    @PostMapping("/signIn")
    private ResponseEntity<ApiResponse<JwtToken>> signIn(
            @RequestBody SignInRequest signInRequest,
            HttpServletResponse response) {

        authService.signIn(signInRequest, response);
        return ResponseBuilder.createApiResponse("로그인 성공", null);
    }

    @PostMapping("/reissue")
    private ResponseEntity<ApiResponse<JwtToken>> reissue(HttpServletRequest request) {

        String refreshToken = request.getHeader("refreshToken");
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
        Member member = jwtTokenProvider.getMemberFromAuthentication(authentication);

        JwtToken token = jwtTokenProvider.reissue(member, refreshToken);
        return ResponseBuilder.createApiResponse("accessToken 재발급 성공", token);
    }
}
