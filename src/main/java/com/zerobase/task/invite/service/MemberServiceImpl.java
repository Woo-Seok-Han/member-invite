package com.zerobase.task.invite.service;

import com.zerobase.task.invite.dto.InviteRequest;
import com.zerobase.task.invite.dto.InviteRequestMapper;
import com.zerobase.task.invite.exception.AuthorityException;
import com.zerobase.task.invite.persistence.InviteRepository;
import com.zerobase.task.invite.persistence.MemberRepository;
import com.zerobase.task.invite.persistence.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final InviteRepository inviteRepository;

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Long member_id) {
        Optional<Member> member = memberRepository.findById(member_id);
        return member.get();
    }


    /**
     * 그룹 매니저 가 아닐시에 exception 발생
     * @param inviteRequest
     */
//    private void validation(InviteRequest inviteRequest){
//
//        if (!member.isManagerYn()) throw new AuthorityException("그룹장만 초대를 생성 할 수 있습니다.");
//    }


}
