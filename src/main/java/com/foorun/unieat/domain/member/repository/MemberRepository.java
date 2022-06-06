package com.foorun.unieat.domain.member.repository;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberJpo, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    
    Optional<MemberJpo> findByEmail(String email);
}
