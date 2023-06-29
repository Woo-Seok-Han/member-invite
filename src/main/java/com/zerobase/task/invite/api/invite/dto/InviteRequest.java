package com.zerobase.task.invite.api.invite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class InviteRequest {

    private Long inviterMemberId;

    @NotBlank
    @Email(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    private Integer age;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

}
