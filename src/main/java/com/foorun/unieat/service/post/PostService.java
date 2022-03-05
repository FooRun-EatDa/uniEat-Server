package com.foorun.unieat.service.post;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.domain.post.repository.PostQuerydslRepository;
import com.foorun.unieat.domain.post.repository.PostRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostQuerydslRepository postQuerydslRepository;

    /**
     * 게시글 단건 상세 조회
     * @param id 게시글 고유 ID
     * @return 게시글 상세 DTO
     */
    @Transactional(readOnly = true)
    public Post fetch(Long id) {
        return Post.of(postQuerydslRepository.find(id)
                .orElseThrow(UniEatNotFoundException::new));
    }

    /**
     * 게시글 단건 저장
     * @return 생성된 게시글 ID
     */
    @Transactional
    public Long save(Post post) {
        PostJpo postJpo = post.asJpo();
        postJpo.setMember(ensureMember(post.getMember().getId()));
        return postRepository.save(postJpo).getId();
    }

    /**
     * 게시글 단건 삭제 --> Hard Delete
     * @return 삭제 처리할 게시글 ID
     */
    @Transactional
    public void removeHard(Long id) {
        postRepository.deleteById(id);
    }

    /**
     * 게시글 단건 삭제 처리 --> Soft Delete
     * @return 삭제 처리할 게시글 ID
     */
    @Transactional
    public void removeSoft(Long id) {
        PostJpo postJpo = postQuerydslRepository.find(id)
                .orElseThrow(UniEatNotFoundException::new);
        postJpo.remove();
    }

    @Transactional(readOnly = true)
    public MemberJpo ensureMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(UniEatForbiddenException::new);
    }
}
