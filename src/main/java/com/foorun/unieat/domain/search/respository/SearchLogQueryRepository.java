package com.foorun.unieat.domain.search.respository;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.search.jpo.QSearchLogJpo;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foorun.unieat.domain.search.jpo.QSearchLogJpo.*;

@Repository
@RequiredArgsConstructor
public class SearchLogQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public List<SearchLogJpo> findSearchLogJpoByMember(MemberJpo memberJpo){
        return jpaQueryFactory.selectFrom(searchLogJpo).where(
                searchLogJpo.member.eq(memberJpo)
        ).fetch();
    }
}
