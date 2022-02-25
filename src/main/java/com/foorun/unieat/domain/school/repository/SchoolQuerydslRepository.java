package com.foorun.unieat.domain.school.repository;

import com.foorun.unieat.domain.school.jpo.SchoolJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foorun.unieat.domain.school.jpo.QSchoolJpo.schoolJpo;

@Repository
@RequiredArgsConstructor
public class SchoolQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<SchoolJpo> findByKeywordRegex(String keyword) {
        return jpaQueryFactory.selectFrom(schoolJpo)
                .where(schoolJpo.name.like("%" + keyword + "%"))
                .innerJoin(schoolJpo.region)
                .fetchJoin()
                .fetch();
    }
}
