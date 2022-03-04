package com.foorun.unieat.service.comment;

import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.comment.repository.CommentRepository;
import com.foorun.unieat.domain.feeling.comment.jpo.CommentFeelingJpo;
import com.foorun.unieat.domain.feeling.comment.repository.CommentFeelingRepository;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentFeelingService {
    private final SecurityContextUtil securityContextUtil;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final CommentFeelingRepository commentFeelingRepository;

    /**
     * 좋아요 생성
     */
    @Transactional
    public void feeling(UserDetails userDetails, long commentId) {
        CommentJpo commentJpo = commentRepository.findById(commentId)
                .orElseThrow(UniEatNotFoundException::new);
        MemberJpo memberJpo = ensureMember(userDetails);
        commentFeelingRepository.save(CommentFeelingJpo.of(memberJpo, commentJpo));
    }

    /**
     * 좋아요 취소
     */
    @Transactional
    public void unfeeling(UserDetails userDetails, long commentId) {
        CommentJpo commentJpo = commentRepository.findById(commentId)
                .orElseThrow(UniEatNotFoundException::new);
        MemberJpo memberJpo = ensureMember(userDetails);
        commentFeelingRepository.deleteByMemberAndComment(memberJpo, commentJpo);
    }

    /**
     * 좋아요 상태인 경우 취소, 아닌경우 좋아요 처리
     * @param commentId 게시글 고유 ID
     */
    @Transactional
    public void toggleFeeling(UserDetails userDetails, long commentId) {
        CommentJpo commentJpo = commentRepository.findById(commentId)
                .orElseThrow(UniEatNotFoundException::new);
        MemberJpo memberJpo = ensureMember(userDetails);
        if (commentFeelingRepository.existsByMemberAndComment(memberJpo, commentJpo)) {
            commentFeelingRepository.deleteByMemberAndComment(memberJpo, commentJpo);
        } else {
            commentFeelingRepository.save(CommentFeelingJpo.of(memberJpo, commentJpo));
        }
    }

    private MemberJpo ensureMember(UserDetails userDetails) {
        return memberRepository.findById(securityContextUtil.getAuthenticationMemberId(userDetails))
                .orElseThrow(UniEatForbiddenException::new);
    }
}
