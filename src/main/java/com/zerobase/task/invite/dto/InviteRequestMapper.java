package com.zerobase.task.invite.dto;

import com.zerobase.task.invite.persistence.entity.Invite;
import com.zerobase.task.invite.persistence.entity.Member;
import com.zerobase.task.invite.persistence.enums.InviteStatus;
import com.zerobase.task.invite.persistence.enums.MemberStatus;
import com.zerobase.task.invite.persistence.enums.Rank;
import com.zerobase.task.invite.persistence.enums.YnValue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InviteRequestMapper {
    InviteRequestMapper INSTANCE = Mappers.getMapper(InviteRequestMapper.class);

    Member InviteRequestToMember(MemberStatus memberStatus, Rank rank, InviteRequest inviteRequest);

    Invite InviteRequestToInvite(String inviteUrl, Long participantMemberId, InviteStatus inviteStatus, InviteRequest inviteRequest);
}
