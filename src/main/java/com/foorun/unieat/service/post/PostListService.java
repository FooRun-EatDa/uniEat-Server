package com.foorun.unieat.service.post;

import com.foorun.unieat.domain.post.dto.PostList;
import com.foorun.unieat.domain.post.repository.PostQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostListService {
    private final PostQuerydslRepository postQuerydslRepository;

    /**
     * 게시글 목록 여러건 조회
     * @param pageRequest 페이징 도메인 객체
     * @return 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<PostList> fetch(PageRequest pageRequest) {
        return postQuerydslRepository.findFetchJoin(pageRequest)
                .stream()
                .map(PostList::of)
                .collect(Collectors.toList());
    }
}
