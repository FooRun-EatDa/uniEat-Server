package com.foorun.unieat.service.post;

import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.post.dto.PostList;
import com.foorun.unieat.domain.post.dto.PostSummaryMap;
import com.foorun.unieat.domain.post.repository.PostMapper;
import com.foorun.unieat.util.StreamUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostListService {
    private final PostMapper postMapper;

    /**
     * 전체 게시판 카테고리별 게시글 목록 조회
     * @return BEST 게시글 n개, 맛집 게시글 n개, 번개 모집 게시글 n개 목록
     */
    @Transactional(readOnly = true)
    public PostSummaryMap fetchSummary() {
        final int TOP_LIMIT_COUNT = 3;
        final List<PostType> postTypes = PostType.asList();
        PostSummaryMap postSummaryMap = new PostSummaryMap();
        postTypes.forEach(postType -> {
            postSummaryMap.add(postType, postMapper
                    .findByPostType(postType,
                            Paging.fromZeroOf(TOP_LIMIT_COUNT),
                            StreamUtil.map(postSummaryMap.getBestType(), PostList::getId)));
        });
        return postSummaryMap;
    }

    /**
     * 게시글 목록 여러건 조회
     * @param pageRequest 페이징 도메인 객체
     * @return 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<PostList> fetch(PostType postType, PageRequest pageRequest) {
        return postMapper.findByPostType(postType, pageRequest);
    }
}
