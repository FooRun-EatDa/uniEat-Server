package com.foorun.unieat.domain.search.respository;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogJpo,Long> {

    List<SearchLogJpo> findSearchLogJpoByMemberJpo(MemberJpo memberJpo);
}
