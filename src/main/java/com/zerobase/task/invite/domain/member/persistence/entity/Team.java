package com.zerobase.task.invite.domain.member.persistence.entity;

import com.zerobase.task.invite.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(name = "NAME")
    private String name;

}
