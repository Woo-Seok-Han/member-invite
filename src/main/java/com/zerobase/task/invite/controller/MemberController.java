package com.zerobase.task.invite.controller;

import com.zerobase.task.invite.persistence.MemberRepository;
import com.zerobase.task.invite.persistence.entity.Member;
import com.zerobase.task.invite.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping(value = "/member/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable("memberId") Long memberId){
        log.info("memberId = {}", memberId);
        return ResponseEntity.ok(memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("존재하지 않은 회원 입니다.")));
    }

    @PostMapping(path = "/member")
    public ResponseEntity<Member> saveMember(@RequestBody Member member) {
        log.info("inviteDto={}", member.toString());
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.ok(savedMember);
    }


}
