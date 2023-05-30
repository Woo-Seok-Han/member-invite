package com.zerobase.task.invite.service;

import com.zerobase.task.invite.dto.InviteRequest;
import com.zerobase.task.invite.persistence.entity.Invite;
import lombok.extern.java.Log;

public interface InviteService {

    public Invite createInvite(InviteRequest inviteRequest);

    public Invite acceptInvite(Long invite_id);

}
