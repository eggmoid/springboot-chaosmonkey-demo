package com.example.demo.api.post.repository;

import java.util.List;

import com.example.demo.api.post.dto.PostRankRes;

public interface PostCustomRepository {

  List<PostRankRes> retrievePostRank();

}
