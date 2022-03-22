package com.foorun.unieat.domain.post.repository;

import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.comment.jpo.QCommentJpo.commentJpo;
import static com.foorun.unieat.domain.feeling.comment.jpo.QCommentFeelingJpo.commentFeelingJpo;
import static com.foorun.unieat.domain.feeling.post.jpo.QPostFeelingJpo.postFeelingJpo;
import static com.foorun.unieat.domain.post.jpo.QPostFileJpo.postFileJpo;
import static com.foorun.unieat.domain.post.jpo.QPostJpo.postJpo;

@Repository
@RequiredArgsConstructor
public class PostQuerydslRepository implements QuerydslSelectMulti<PostJpo>, QuerydslSelectSingle<PostJpo, Long> {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostJpo> find(Pageable pageable) {
        return jpaQueryFactory.selectFrom(postJpo)
                .innerJoin(postJpo.comments)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Optional<PostJpo> find(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(postJpo)
                .leftJoin(postJpo.comments, commentJpo)
                .fetchJoin()
                .leftJoin(commentJpo.feelings, commentFeelingJpo)
                .fetchJoin()
                .innerJoin(commentFeelingJpo.member)
                .fetchJoin()
                .leftJoin(commentJpo.comments)
                .fetchJoin()
                .leftJoin(postJpo.postFeelings, postFeelingJpo)
                .fetchJoin()
                .leftJoin(postFeelingJpo.member)
                .fetchJoin()
                .leftJoin(postJpo.files, postFileJpo)
                .fetchJoin()
                .leftJoin(postFileJpo.file)
                .fetchJoin()
                .orderBy(commentJpo.parent.id.asc(), commentJpo.id.asc())
                .where(postJpo.id.eq(id))
                .fetchOne());
    }
}
