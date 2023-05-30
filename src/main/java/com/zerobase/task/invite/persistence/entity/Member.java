package com.zerobase.task.invite.persistence.entity;

import com.zerobase.task.invite.persistence.converter.MemberStatusConverter;
import com.zerobase.task.invite.persistence.converter.RankConverter;
import com.zerobase.task.invite.persistence.converter.YnConverter;
import com.zerobase.task.invite.persistence.enums.MemberStatus;
import com.zerobase.task.invite.persistence.enums.Rank;
import com.zerobase.task.invite.persistence.enums.YnValue;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "MEMBER")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String email;

    @Convert(converter = RankConverter.class)
    private Rank rank;

    @Convert(converter = MemberStatusConverter.class)
    private MemberStatus memberStatus;

}
