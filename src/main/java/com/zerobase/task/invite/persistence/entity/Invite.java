package com.zerobase.task.invite.persistence.entity;

import com.zerobase.task.invite.persistence.converter.InviteStatusConverter;
import com.zerobase.task.invite.persistence.enums.InviteStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "Invite")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inviteId;

    private Long inviterMemberId;

    private Long participantMemberId;

    private String inviteUrl;

    @Convert(converter = InviteStatusConverter.class)
    private InviteStatus inviteStatus;
}
