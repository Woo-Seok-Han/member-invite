package com.zerobase.task.invite.api.invite.dto;

import com.zerobase.task.invite.domain.invite.persistence.entity.Invite;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import com.zerobase.task.invite.domain.invite.constant.InviteStatus;
import com.zerobase.task.invite.domain.member.constant.MemberStatus;
import com.zerobase.task.invite.domain.member.constant.MemberRank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InviteRequestMapper {

    InviteRequestMapper INSTANCE = Mappers.getMapper(InviteRequestMapper.class);

    @Mapping(target = "memberId", ignore = true)
    Member InviteRequestToMember(
        MemberStatus memberStatus, MemberRank memberRank, InviteRequest inviteRequest);

    @Mapping(target = "inviteId", ignore = true)
    Invite InviteRequestToInvite(
        String inviteUrl,
        Long participantMemberId,
        InviteStatus inviteStatus,
        InviteRequest inviteRequest);
}
