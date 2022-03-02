package com.foorun.unieat.domain.post.dto;

import com.foorun.unieat.domain.common.PostType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostSummaryMap extends LinkedHashMap<PostType, List<PostList>> {
    public void add(PostType postType, List<PostList> postLists) {
        this.put(postType, postLists);
    }

    public List<PostList> getByType(PostType postType) {
        return this.getOrDefault(postType, Collections.emptyList());
    }

    public List<PostList> getBestType() {
        return this.getByType(PostType.BEST);
    }
}
