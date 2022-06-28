package com.foorun.unieat.domain.hashtag.repository;

import com.foorun.unieat.domain.hashtag.jpo.HashTagJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagRepository extends JpaRepository<HashTagJpo, Long> {
    List<HashTagJpo> findByIdIn(List<Long> ids);
}
