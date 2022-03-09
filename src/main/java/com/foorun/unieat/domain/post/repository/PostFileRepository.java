package com.foorun.unieat.domain.post.repository;

import com.foorun.unieat.domain.post.jpo.PostFileJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFileRepository extends JpaRepository<PostFileJpo, PostFileJpo> {
}
