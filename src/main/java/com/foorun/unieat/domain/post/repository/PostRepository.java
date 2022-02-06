package com.foorun.unieat.domain.post.repository;

import com.foorun.unieat.domain.post.jpo.PostJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostJpo, Long> {
}
