package com.zerobase.task.invite.controller;

import com.zerobase.task.invite.dto.InviteRequest;
import com.zerobase.task.invite.persistence.entity.Invite;
import com.zerobase.task.invite.service.InviteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InviteController {

    private final InviteService inviteService;

    @PostMapping(path = "/invite")
    public ResponseEntity createInvite(@RequestBody InviteRequest InviteRequest) {
        log.info("inviteDto={}", InviteRequest.toString());
        return ResponseEntity.ok(inviteService.createInvite(InviteRequest));
    }

    @PutMapping(path = "/invite/{inviteId}")
    public ResponseEntity acceptInvite(@PathVariable Long inviteId) {
        log.info("invite_id={}", inviteId);
        Invite acceptedInvite = inviteService.acceptInvite(inviteId);
        return ResponseEntity.ok(acceptedInvite);
    }

}
