package com.foorun.unieat.service.post;

import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.domain.post.repository.PostQuerydslRepository;
import com.foorun.unieat.domain.post.repository.PostRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Post fetch(Long id) {
        return Post.of(postQuerydslRepository.find(id)
                .orElseThrow(UniEatNotFoundException::new));
    }

    /**
     * 게시글 단건 저장
     * @return 생성된 게시글 ID
     */
    public Long save(Post post) {
        PostJpo postJpo = post.asJpo();
        postJpo.setMember(memberRepository.findById(post.getMemberId())
                .orElseThrow(UniEatForbiddenException::new));
        return postRepository.save(postJpo).getId();
    }

    /**
     * 게시글 단건 삭제
     * @return 생성된 게시글 ID
     */
    public void remove(Long id) {
        postRepository.deleteById(id);
    }
}
