package com.foorun.unieat.domain.comment.repository;

import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.comment.jpo.CommentJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.comment.jpo.QCommentJpo.commentJpo;
import static com.foorun.unieat.domain.feeling.comment.jpo.QCommentFeelingJpo.commentFeelingJpo;

@Repository
@RequiredArgsConstructor
public class CommentQuerydslRepository implements QuerydslSelectSingle<CommentJpo, Long> {
    private final JPAQueryFactory jpaQueryFactory;

    public List<CommentJpo> find(long postId, Pageable pageable) {
        //  TODO : Fix Fetch join
        return jpaQueryFactory.selectFrom(commentJpo)
                .innerJoin(commentJpo.member)
                .fetchJoin()
                .leftJoin(commentJpo.comments)
                .fetchJoin()
                .leftJoin(commentJpo.feelings)
                .fetchJoin()
                .where(commentJpo.post.id.eq(postId))
                .where(commentJpo.parent.isNull())
                .orderBy(commentJpo.parent.id.asc(), commentJpo.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Optional<CommentJpo> find(Long id) {
        //  TODO : Fix Fetch join
        return Optional.ofNullable(jpaQueryFactory.selectFrom(commentJpo)
                .distinct()
                .innerJoin(commentJpo.member)
                .fetchJoin()
                .leftJoin(commentJpo.comments)
                .fetchJoin()
                .leftJoin(commentJpo.feelings, commentFeelingJpo)
                .fetchJoin()
                .leftJoin(commentFeelingJpo.member)
                .fetchJoin()
                .where(commentJpo.id.eq(id))
                .orderBy(commentJpo.id.asc())
                .fetchOne());
    }
}
