package com.zerobase.task.invite.api.invite.controller;

import com.zerobase.task.invite.api.common.model.ApiResponse;
import com.zerobase.task.invite.api.common.model.constant.ErrorCode;
import com.zerobase.task.invite.api.invite.dto.InviteRequest;
import com.zerobase.task.invite.domain.invite.persistence.entity.Invite;
import com.zerobase.task.invite.domain.invite.service.InviteService;
import com.zerobase.task.invite.domain.member.persistence.MemberRepository;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import com.zerobase.task.invite.global.error.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InviteController {

    private final InviteService inviteService;

    @PostMapping(path = "/invite")
    public ResponseEntity createInvite(@Valid @RequestBody final InviteRequest InviteRequest) {
        log.info("inviteDto={}", InviteRequest.toString());
        return ApiResponse.createSuccess(inviteService.createInvite(InviteRequest));
    }

    @GetMapping(path = "/invite/verify")
    public ResponseEntity acceptInvite(final String email, final String code ) {
        Invite acceptedInvite = inviteService.acceptInvite(email, code);
        return ApiResponse.createSuccess(acceptedInvite);
    }
}
