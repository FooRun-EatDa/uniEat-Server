package com.foorun.unieat.domain.search.respository;

import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogJpo,Long> {

}
