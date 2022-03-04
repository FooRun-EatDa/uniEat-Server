package com.foorun.unieat.domain.comment.repository;

import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentJpo, Long> {
}
