package com.example.demo.api.post.repository;

import java.util.List;

import com.example.demo.api.post.entity.PostEntity;

public interface PostCustomRepository {

  List<PostEntity> retrievePostRank();

}
