package com.foorun.unieat.domain.post.repository;

import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.post.dto.PostList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

@Mapper
public interface PostMapper {
    List<PostList> findByPostType(PostType postType, Pageable pageable, List<Long> excludeIds);
    
    default List<PostList> findByPostType(PostType postType, Pageable pageable) {
        return findByPostType(postType, pageable, Collections.emptyList());
    }
}
