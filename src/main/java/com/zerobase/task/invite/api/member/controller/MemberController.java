package com.zerobase.task.invite.api.member.controller;

import com.zerobase.task.invite.api.common.model.ApiResponse;
import com.zerobase.task.invite.global.error.exception.BusinessException;
import com.zerobase.task.invite.api.common.model.constant.ErrorCode;
import com.zerobase.task.invite.infra.mail.MailService;
import com.zerobase.task.invite.domain.member.persistence.MemberRepository;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    private final MailService mailService;

    @GetMapping(value = "/member")
    public ResponseEntity<List<Member>> getAllMember() {
        List<Member> memberList = memberRepository.findAll();

        return ResponseEntity.ok(memberList);
    }

    @GetMapping(value = "/member/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable("memberId") final Long memberId) {
        // return ResponseEntity.ok(memberRepository.findById(memberId).orElseThrow(() -> new
        // BusinessException(ErrorCode.MEMBER_NOT_FOUND)));
        return ApiResponse.createSuccess(
            memberRepository
                .findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND)));
    }

    @PostMapping(path = "/member")
    public ResponseEntity<Member> saveMember(@RequestBody final Member member) {
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.ok(savedMember);
    }

//    @GetMapping(value = "/mail/send")
//    public ResponseEntity<String> sendMail() {
//        mailService.sendMail("woosuk1893@naver.com", "테스트 메일", "<h1>안녕하세요 메일 테스트 입니다.</h1>");
//        return ResponseEntity.ok("메일이 성공적으로 발송 되었습니다.");
//    }
}
