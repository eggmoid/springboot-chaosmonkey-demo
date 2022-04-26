package com.example.demo.api.post.repository;

import java.util.List;

import com.example.demo.api.post.dto.PostRankRes;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.example.demo.api.post.entity.QPostEntity.postEntity;

@RequiredArgsConstructor
@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository {

  // private final QPostEntity qPostEntity = QPostEntity.postEntity;

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<PostRankRes> retrievePostRank() {
    NumberPath<Long> postCount = Expressions.numberPath(Long.class, "postCount");

    return jpaQueryFactory
        .select(Projections.constructor(PostRankRes.class,
            Expressions.asNumber(0L), // postRank
            postEntity.count().as(postCount),
            postEntity.postAuthor, // postAuthor: Java 코드로 join
            postEntity.postId,
            postEntity.postIp,
            postEntity.postCommentCount.sum(),
            postEntity.postGallCount.sum(),
            postEntity.postGallRecommend.sum()))
        .from(postEntity)
        .groupBy(postEntity.postId, postEntity.postIp, postEntity.postAuthor)
        .fetch();
  }

}
