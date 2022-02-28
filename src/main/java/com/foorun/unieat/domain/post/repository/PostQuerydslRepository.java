package com.foorun.unieat.domain.post.repository;

import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.comment.jpo.QCommentJpo.commentJpo;
import static com.foorun.unieat.domain.feeling.post.jpo.QPostFeelingJpo.postFeelingJpo;
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
                .innerJoin(postJpo.comments, commentJpo)
                .fetchJoin()
                .leftJoin(commentJpo.comments)
                .fetchJoin()
                .leftJoin(postJpo.postFeelings, postFeelingJpo)
                .fetchJoin()
                .leftJoin(postFeelingJpo.member)
                .fetchJoin()
                .orderBy(commentJpo.parent.id.asc(), commentJpo.id.asc())
                .where(postJpo.id.eq(id))
                .fetchOne());
    }

    /**
     *
     * @param postType 게시글 카테고리 구분
     * @param pageable {@link Pageable}
     * @param excludeIds 조회 결과에 제외할 게시글 고유 ID 목록
     */
    public List<PostJpo> findByPostType(PostType postType, Pageable pageable, List<Long> excludeIds) {
        if (postType.isBest()) {
            return findByBestType(pageable);
        }
        return jpaQueryFactory.selectFrom(postJpo)
                .distinct()
                .leftJoin(postJpo.comments, commentJpo)
                .fetchJoin()
                .leftJoin(commentJpo.comments)
                .fetchJoin()
                .leftJoin(postJpo.postFeelings, postFeelingJpo)
                .fetchJoin()
                .leftJoin(postFeelingJpo.member)
                .fetchJoin()
                .where(postJpo.id.notIn(excludeIds))
                .where(postJpo.id.in(jpaQueryFactory
                        .select(postJpo.id)
                        .from(postJpo)
                        .where(postJpo.category.eq(postType))
                        .orderBy(postJpo.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch()))
                .orderBy(postJpo.createdAt.desc(), commentJpo.parent.id.asc(), commentJpo.id.asc())
                .fetch();
    }

    public List<PostJpo> findByBestType(Pageable pageable) {
        return jpaQueryFactory.selectFrom(postJpo)
                .distinct()
                .leftJoin(postJpo.comments, commentJpo)
                .fetchJoin()
                .leftJoin(commentJpo.comments)
                .fetchJoin()
                .leftJoin(postJpo.postFeelings, postFeelingJpo)
                .fetchJoin()
                .leftJoin(postFeelingJpo.member)
                .fetchJoin()
                .where(postJpo.id.in(jpaQueryFactory
                        .select(postJpo.id)
                        .from(postJpo)
                        .leftJoin(postJpo.postFeelings, postFeelingJpo)
                        .fetchJoin()
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch()))
                .orderBy(postFeelingJpo.count().desc(), commentJpo.parent.id.asc(), commentJpo.id.asc())
                .fetch();
    }
}
