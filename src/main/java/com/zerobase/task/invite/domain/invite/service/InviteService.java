package com.zerobase.task.invite.domain.invite.service;

import com.zerobase.task.invite.api.invite.dto.InviteRequest;
import com.zerobase.task.invite.domain.invite.persistence.entity.Invite;

public interface InviteService {

    public Invite createInvite(InviteRequest inviteRequest);

    public Invite acceptInvite(Long inviteId);

    public Invite getInvite(Long inviteId);

}
