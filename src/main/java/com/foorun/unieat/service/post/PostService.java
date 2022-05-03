package com.foorun.unieat.service.post;

import com.foorun.unieat.annotation.Validation;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.dto.PostSavePayload;
import com.foorun.unieat.domain.post.jpo.PostFileJpo;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.domain.post.repository.PostFileRepository;
import com.foorun.unieat.domain.post.repository.PostQuerydslRepository;
import com.foorun.unieat.domain.post.repository.PostRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostQuerydslRepository postQuerydslRepository;
    private final PostFileRepository postFileRepository;

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
     * 게시글 단건 생성
     * @return 생성된 게시글 ID
     */
    @Validation
    @Transactional
    public Long save(MemberUserDetails memberUserDetails, PostSavePayload postSavePayload) {
        PostJpo postJpo = postSavePayload.asJpo();
        postJpo.setMember(ensureMember(memberUserDetails));
        Long postId = postRepository.save(postJpo).getId();
        saveFiles(postJpo, postSavePayload);
        removeFiles(postJpo, postSavePayload);
        return postId;
    }

    /**
     * 게시글 단건 수정
     * @return 게시글 ID
     */
    @Validation
    @Transactional
    public Long modify(MemberUserDetails memberUserDetails, PostSavePayload postSavePayload) {
        PostJpo postJpo = postSavePayload.asJpo();
        postJpo.setMember(ensureMember(memberUserDetails));
        Long postId = postRepository.save(postJpo).getId();
        saveFiles(postJpo, postSavePayload);
        removeFiles(postJpo, postSavePayload);
        return postId;
    }

    private void removeFiles(PostJpo postJpo, PostSavePayload postSavePayload) {
        List<PostFileJpo> removeFiles = postSavePayload.filterRemoveFilesAsJpo(postJpo);
        if (!removeFiles.isEmpty()) {
            postFileRepository.deleteAll(removeFiles);
        }
    }

    private void saveFiles(PostJpo postJpo, PostSavePayload postSavePayload) {
        List<PostFileJpo> newFiles = postSavePayload.filterNewFilesAsJpo(postJpo);
        if (!newFiles.isEmpty()) {
            postFileRepository.saveAll(newFiles);
        }
    }

    /**
     * 게시글 단건 삭제 --> Hard Delete
     * @return 삭제 처리할 게시글 ID
     */
    @Transactional
    public void removeHard(MemberUserDetails memberUserDetails, Long id) {
        PostJpo postJpo = postQuerydslRepository.find(id)
                .orElseThrow(UniEatNotFoundException::new);
        if (!postJpo.getMember().equals(memberUserDetails.getId())) {
            throw new UniEatForbiddenException();
        }
        postRepository.deleteById(id);
    }

    /**
     * 게시글 단건 삭제 처리 --> Soft Delete
     * @return 삭제 처리할 게시글 ID
     */
    @Transactional
    public void removeSoft(MemberUserDetails memberUserDetails, Long id) {
        PostJpo postJpo = postQuerydslRepository.find(id)
                .orElseThrow(UniEatNotFoundException::new);
        if (!postJpo.getMember().equals(memberUserDetails.getId())) {
            throw new UniEatForbiddenException();
        }
        postJpo.remove();
    }

    @Transactional(readOnly = true)
    public MemberJpo ensureMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(UniEatForbiddenException::new);
    }

    public MemberJpo ensureMember(MemberUserDetails memberUserDetails) {
        return ensureMember(memberUserDetails.getId());
    }
}
