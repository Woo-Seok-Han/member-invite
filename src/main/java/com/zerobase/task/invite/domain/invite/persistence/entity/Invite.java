package com.zerobase.task.invite.domain.invite.persistence.entity;

import com.zerobase.task.invite.domain.common.BaseEntity;
import com.zerobase.task.invite.domain.invite.constant.InviteStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inviteId;

    private Long inviterMemberId;

    private Long participantMemberId;

    private String inviteUrl;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;

    public Invite(Long inviterMemberId, Long participantMemberId, String inviteUrl) {
        this.inviteId = null;
        this.inviterMemberId = inviterMemberId;
        this.participantMemberId = participantMemberId;
        this.inviteUrl = inviteUrl;
        this.inviteStatus = InviteStatus.VALID;
    }

    public void expire() {
        this.inviteStatus = InviteStatus.EXPIRED;
    }
}
