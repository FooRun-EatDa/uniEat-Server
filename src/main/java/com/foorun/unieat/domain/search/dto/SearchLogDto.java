package com.foorun.unieat.domain.search.dto;


import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SearchLogDto {

    @Builder.Default
    private Long id = IdentifyGenerator.number();

    private String searchText;

    public static SearchLogJpo createEmpty() {
        return new SearchLogJpo();
    }
    public SearchLogJpo asJpo() {
        SearchLogJpo searchLogJpo = new SearchLogJpo();
        BeanUtils.copyProperties(this, searchLogJpo);
        return searchLogJpo;
    }

}
