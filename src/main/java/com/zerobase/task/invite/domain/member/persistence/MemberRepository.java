package com.zerobase.task.invite.domain.member.persistence;

import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}