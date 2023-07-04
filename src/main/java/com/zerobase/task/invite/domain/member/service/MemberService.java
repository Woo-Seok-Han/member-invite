package com.zerobase.task.invite.domain.member.service;

import com.zerobase.task.invite.global.error.exception.BusinessException;
import com.zerobase.task.invite.api.common.model.constant.ErrorCode;
import com.zerobase.task.invite.domain.member.persistence.MemberRepository;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    public Member getMember(Long member_id) {
        return memberRepository.findById(member_id)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
