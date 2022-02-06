package com.foorun.unieat.service.comment;

import com.foorun.unieat.domain.comment.dto.Comment;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.domain.post.repository.PostRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;

    /**
     * 댓글 단건 저장
     * @param comment 댓글 DTO
     */
    @Transactional
    public void save(Comment comment) {
        PostJpo postJpo = postRepository.findById(comment.getPostId())
                .orElseThrow(UniEatNotFoundException::new);

        postJpo.addComment(comment.asJpo());
    }
}
