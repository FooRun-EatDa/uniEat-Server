package com.foorun.unieat.domain.comment.repository;

import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentJpo, Long> {
    Optional<CommentJpo> findByIdAndMember(long id, MemberJpo memberJpo);
}
