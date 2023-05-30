package com.zerobase.task.invite.service;

import com.zerobase.task.invite.dto.InviteRequest;
import com.zerobase.task.invite.persistence.entity.Member;

public interface MemberService {
    public Member getMember(Long member_id);

}
