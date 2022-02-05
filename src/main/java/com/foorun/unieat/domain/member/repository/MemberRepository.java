package com.foorun.unieat.domain.member.repository;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberJpo, Long> {
}
