package com.foorun.unieat.service.comment;

import com.foorun.unieat.domain.comment.dto.Comment;
import com.foorun.unieat.domain.comment.repository.CommentQuerydslRepository;
import com.foorun.unieat.domain.common.paging.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentListService {
    private final CommentQuerydslRepository commentQuerydslRepository;

    /**
     * 게시글 목록 여러건 조회
     * @param paging 페이징 도메인 객체
     * @return 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Comment> fetch(long postId, Paging paging) {
        return commentQuerydslRepository.find(postId, paging)
                .stream()
                .map(Comment::of)
                .collect(Collectors.toList());
    }
}
