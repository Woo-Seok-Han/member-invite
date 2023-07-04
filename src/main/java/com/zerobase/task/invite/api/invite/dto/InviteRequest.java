package com.zerobase.task.invite.api.invite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
public class InviteRequest {

    @NotNull(message = "초대자가 존재 하지 않습니다.")
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
