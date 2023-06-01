package com.zerobase.task.invite.service;

import com.zerobase.task.invite.dto.InviteRequest;
import com.zerobase.task.invite.persistence.entity.Invite;

public interface InviteService {

    public Invite createInvite(InviteRequest inviteRequest);

    public Invite acceptInvite(Long inviteId);

    public Invite getInvite(Long inviteId);

}
