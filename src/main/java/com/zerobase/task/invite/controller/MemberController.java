package com.zerobase.task.invite.controller;

import com.zerobase.task.invite.dto.InviteRequest;
import com.zerobase.task.invite.persistence.MemberRepository;
import com.zerobase.task.invite.persistence.entity.Member;
import com.zerobase.task.invite.service.MemberService;
import com.zerobase.task.invite.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping(value = "/member/{member_id}")
    @ResponseBody
    public ResponseEntity<Member> getMember(@PathVariable("member_id") Long member_id){
        log.info("member_id = {}", member_id);
        Optional<Member> byId = memberRepository.findById(member_id);
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping(path = "/member")
    @ResponseBody
    public ResponseEntity<Member> saveMember(@RequestBody Member member) {
        log.info("inviteDto={}", member.toString());
        Member saved = memberRepository.save(member);
        return ResponseEntity.ok(saved);
    }


}
