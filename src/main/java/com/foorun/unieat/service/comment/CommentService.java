package com.foorun.unieat.service.comment;

import com.foorun.unieat.domain.comment.dto.Comment;
import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.foorun.unieat.domain.comment.repository.CommentQuerydslRepository;
import com.foorun.unieat.domain.comment.repository.CommentRepository;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentQuerydslRepository commentQuerydslRepository;
    private final MemberRepository memberRepository;
    private final SecurityContextUtil securityContextUtil;

    /**
     * 댓글 단건 상세 조회
     * @param commentId 게시글 고유 ID
     * @return 댓글 상세 DTO
     */
    @Transactional(readOnly = true)
    public Comment fetch(long commentId) {
        return Comment.of(commentQuerydslRepository.find(commentId)
                .orElseThrow(UniEatNotFoundException::new));
    }

    /**
     * 댓글 단건 삭제 --> Hard Delete
     */
    @Transactional
    public void removeHard(UserDetails userDetails, long commentId) {
        MemberJpo memberJpo = ensureMember(userDetails);
        CommentJpo commentJpo = commentRepository.findByIdAndMember(commentId, memberJpo)
                .orElseThrow(UniEatNotFoundException::new);
        commentRepository.deleteById(commentId);
    }

    /**
     * 댓글 단건 삭제 처리 --> Soft Delete
     */
    @Transactional
    public void removeSoft(UserDetails userDetails, long commentId) {
        MemberJpo memberJpo = ensureMember(userDetails);
        CommentJpo commentJpo = commentRepository.findByIdAndMember(commentId, memberJpo)
                .orElseThrow(UniEatNotFoundException::new);
        commentJpo.remove();
    }

    /**
     * 댓글 단건 저장
     * @param comment 댓글 DTO
     */
    @Transactional
    public void save(UserDetails userDetails, Comment comment) {
        ensureMember(userDetails);
        commentRepository.save(comment.asJpo());
    }

    private MemberJpo ensureMember(UserDetails userDetails) {
        return memberRepository.findById(securityContextUtil.getAuthenticationMemberId(userDetails))
                .orElseThrow(UniEatForbiddenException::new);
    }
}
