package com.zerobase.task.invite.domain.member.persistence.entity;

import com.zerobase.task.invite.domain.common.BaseEntity;
import com.zerobase.task.invite.domain.member.constant.MemberRank;
import com.zerobase.task.invite.domain.member.constant.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Table(name = "MEMBER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Member(String name, Integer age, String phoneNumber, String email) {
        this.memberId = null;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.memberRank = MemberRank.EMPLOYEE;
        this.memberStatus = MemberStatus.TEMPORARY;
    }

    public void updateRegular() {
        this.memberStatus = MemberStatus.REGULAR;
    }
}
