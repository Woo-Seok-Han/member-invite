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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inviteId;

    private Long inviterMemberId;

    private Long participantMemberId;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;

    public Invite(Long inviterMemberId, Long participantMemberId) {
        this.inviteId = null;
        this.inviterMemberId = inviterMemberId;
        this.participantMemberId = participantMemberId;
        this.inviteStatus = InviteStatus.VALID;
    }

    public void expire() {
        this.inviteStatus = InviteStatus.EXPIRED;
    }
}
