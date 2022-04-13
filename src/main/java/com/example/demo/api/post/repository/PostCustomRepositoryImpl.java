package com.example.demo.api.post.repository;

import java.util.List;

import com.example.demo.api.post.entity.PostEntity;
import com.example.demo.api.post.entity.QPostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

// import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public PostCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<PostEntity> retrievePostRank() {
    // return null;
    return jpaQueryFactory.selectFrom(QPostEntity.postEntity).fetch();
  }

}
