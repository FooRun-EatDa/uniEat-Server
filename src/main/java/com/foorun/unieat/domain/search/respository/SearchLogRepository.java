package com.foorun.unieat.domain.search.respository;

import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogJpo,Long> {

    @Query("SELECT s from SearchLogJpo s inner join s.member m where m.id = :memberId ")
    Page<SearchLogJpo> findSearchLogJpoByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT s from SearchLogJpo s inner join s.member m where m.id = :memberId and s.searchText = :text")
    SearchLogJpo findSearchLogJpoByMemberIdAndSearchText(@Param("memberId") Long memberId, @Param("text") String text);


    @Modifying
    @Query("DELETE FROM SearchLogJpo s where s.member.id = :memberId and s.searchText = :text")
    Integer deleteSearchLogJpoByMemberId(@Param("memberId") Long memberId, @Param("text") String text);

}
