package com.zerobase.task.invite.domain.invite.persistence;

import com.zerobase.task.invite.domain.invite.persistence.entity.Invite;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    public Optional<Invite> findByParticipantMemberId(Long participantMemberId);
}