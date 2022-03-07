package com.foorun.unieat.domain.search.respository;

import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogJpo,Long> {

    List<SearchLogJpo> findSearchLogJpoByMember(MemberJpo memberJpo, PageRequest pageable);
}
