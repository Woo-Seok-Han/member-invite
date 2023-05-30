package com.zerobase.task.invite.dto;

import com.zerobase.task.invite.persistence.enums.Rank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InviteRequest {

    private Long inviterMemberId;

    @NotBlank
    @Email(message = "이메일을 양식을 지켜주세요.")
    private String email;
    @NotBlank
    private String name;

    private Integer age;

    @NotBlank
    private String phoneNumber;
}
