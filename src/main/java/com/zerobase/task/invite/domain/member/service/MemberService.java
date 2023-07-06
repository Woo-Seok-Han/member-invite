package com.zerobase.task.invite.domain.member.service;

import com.zerobase.task.invite.domain.member.persistence.entity.Member;

public interface MemberService {
    public Member getMember(Long member_id);

}
