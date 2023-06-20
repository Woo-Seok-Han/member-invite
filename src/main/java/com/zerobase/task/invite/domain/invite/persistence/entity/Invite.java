package com.zerobase.task.invite.domain.invite.persistence.entity;

import com.zerobase.task.invite.domain.common.BaseEntity;
import com.zerobase.task.invite.domain.invite.persistence.enums.InviteStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Invite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inviteId;

    private Long inviterMemberId;

    private Long participantMemberId;

    private String inviteUrl;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;
}
