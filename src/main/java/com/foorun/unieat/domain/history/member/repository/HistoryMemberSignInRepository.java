package com.foorun.unieat.domain.history.member.repository;

import com.foorun.unieat.domain.history.member.jpo.HistoryMemberSignInJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryMemberSignInRepository extends JpaRepository<HistoryMemberSignInJpo, Long> {
}
