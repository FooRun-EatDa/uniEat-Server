package com.foorun.unieat.domain.search.dto;


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
public class SearchLog {

    @Builder.Default
    private Long id = IdentifyGenerator.number();

    private String searchText;

    public static SearchLog createEmpty() {
        return new SearchLog();
    }

    public static SearchLog of(SearchLogJpo searchLogJpo){
        SearchLog searchLogDto = createEmpty();
        BeanUtils.copyProperties(searchLogJpo,searchLogDto);
        return searchLogDto;
    }

    public SearchLogJpo asJpo() {
        SearchLogJpo searchLogJpo = new SearchLogJpo();
        BeanUtils.copyProperties(this, searchLogJpo);
        return searchLogJpo;
    }

}
