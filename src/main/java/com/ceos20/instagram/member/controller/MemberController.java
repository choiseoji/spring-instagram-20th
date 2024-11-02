package com.ceos20.instagram.member.controller;

import com.ceos20.instagram.common.annotation.Login;
import com.ceos20.instagram.member.domain.Member;
import com.ceos20.instagram.member.dto.GetMemberInfoResponse;
import com.ceos20.instagram.member.dto.UpdateMemberInfoRequest;
import com.ceos20.instagram.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PatchMapping
    public ResponseEntity updateMemberInfo(
            @RequestBody UpdateMemberInfoRequest updateMemberInfoRequest,
            @Login Member member) {

        memberService.updateMemberInfo(updateMemberInfoRequest, member);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<GetMemberInfoResponse> getMemberInfoById(@Login Member member) {

        GetMemberInfoResponse response = memberService.getMemberInfoById(member.getId());
        return ResponseEntity.ok(response);
    }
}
