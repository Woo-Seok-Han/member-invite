package com.zerobase.task.invite.domain.member.persistence.entity;

import com.zerobase.task.invite.domain.common.BaseEntity;
import com.zerobase.task.invite.domain.member.persistence.enums.MemberRank;
import com.zerobase.task.invite.domain.member.persistence.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "MEMBER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private MemberRank memberRank;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

}
