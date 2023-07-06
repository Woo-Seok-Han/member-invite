package com.zerobase.task.invite.domain.member.persistence;

import com.zerobase.task.invite.domain.invite.persistence.entity.Invite;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
}