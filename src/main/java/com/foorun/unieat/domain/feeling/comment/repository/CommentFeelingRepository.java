package com.foorun.unieat.domain.feeling.comment.repository;

import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.feeling.comment.jpo.CommentFeelingIdJpo;
import com.foorun.unieat.domain.feeling.comment.jpo.CommentFeelingJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentFeelingRepository extends JpaRepository<CommentFeelingJpo, CommentFeelingIdJpo> {
    Optional<CommentFeelingJpo> findByMemberAndComment(MemberJpo memberJpo, CommentJpo commentJpo);
    boolean existsByMemberAndComment(MemberJpo memberJpo, CommentJpo commentJpo);
    void deleteByMemberAndComment(MemberJpo memberJpo, CommentJpo commentJpo);
}
