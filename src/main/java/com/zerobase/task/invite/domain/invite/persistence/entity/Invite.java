package com.zerobase.task.invite.domain.invite.persistence.entity;

import com.zerobase.task.invite.domain.common.BaseEntity;
import com.zerobase.task.invite.domain.common.util.RandomCode;
import com.zerobase.task.invite.domain.invite.constant.InviteStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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

    @Column(unique = true)
    private Long participantMemberId;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;

    private String verificationCode;

    private LocalDateTime verifyExpiredAt;

    public Invite(Long inviterMemberId, Long participantMemberId, String verificationCode) {
        this.inviteId = null;
        this.inviterMemberId = inviterMemberId;
        this.participantMemberId = participantMemberId;
        this.inviteStatus = InviteStatus.VALID;
        this.verificationCode = verificationCode;
        this.verifyExpiredAt = LocalDateTime.now().plusDays(1);
    }

    public void expire() {
        this.inviteStatus = InviteStatus.EXPIRED;
    }
}
