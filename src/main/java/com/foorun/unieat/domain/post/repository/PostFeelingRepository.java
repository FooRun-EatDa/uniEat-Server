package com.foorun.unieat.domain.post.repository;

import com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostFeelingRepository extends JpaRepository<PostFeelingJpo, PostFeelingJpo> {
    Optional<PostFeelingJpo> findByMemberAndPost(MemberJpo memberJpo, PostJpo postJpo);
    boolean existsByMemberAndPost(MemberJpo memberJpo, PostJpo postJpo);
    void deleteByMemberAndPost(MemberJpo memberJpo, PostJpo postJpo);
}
