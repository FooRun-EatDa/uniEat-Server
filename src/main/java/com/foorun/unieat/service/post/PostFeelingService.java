package com.foorun.unieat.service.post;

import com.foorun.unieat.domain.feeling.post.jpo.PostFeelingJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.domain.feeling.post.repository.PostFeelingRepository;
import com.foorun.unieat.domain.post.repository.PostQuerydslRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostFeelingService {
    private final SecurityContextUtil securityContextUtil;
    private final PostQuerydslRepository postQuerydslRepository;
    private final MemberRepository memberRepository;
    private final PostFeelingRepository postFeelingRepository;

    /**
     * 좋아요 생성
     */
    @Transactional
    public void feeling(UserDetails userDetails, long postId) {
        PostJpo postJpo = postQuerydslRepository.find(postId)
                .orElseThrow(UniEatNotFoundException::new);
        MemberJpo memberJpo = ensureMember(userDetails);
        postFeelingRepository.save(PostFeelingJpo.of(postJpo, memberJpo));
    }

    /**
     * 좋아요 취소
     */
    @Transactional
    public void unfeeling(UserDetails userDetails, long postId) {
        PostJpo postJpo = postQuerydslRepository.find(postId)
                .orElseThrow(UniEatNotFoundException::new);
        MemberJpo memberJpo = ensureMember(userDetails);
        postFeelingRepository.deleteByMemberAndPost(memberJpo, postJpo);
    }

    /**
     * 좋아요 상태인 경우 취소, 아닌경우 좋아요 처리
     * @param postId 게시글 고유 ID
     */
    @Transactional
    public void toggleFeeling(UserDetails userDetails, long postId) {
        PostJpo postJpo = postQuerydslRepository.find(postId)
                .orElseThrow(UniEatNotFoundException::new);
        MemberJpo memberJpo = ensureMember(userDetails);
        if (postFeelingRepository.existsByMemberAndPost(memberJpo, postJpo)) {
            postFeelingRepository.deleteByMemberAndPost(memberJpo, postJpo);
        } else {
            postFeelingRepository.save(PostFeelingJpo.of(postJpo, memberJpo));
        }
    }

    private MemberJpo ensureMember(UserDetails userDetails) {
        return memberRepository.findById(securityContextUtil.getAuthenticationMemberId(userDetails))
                .orElseThrow(UniEatForbiddenException::new);
    }
}
