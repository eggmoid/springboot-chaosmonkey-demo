package com.example.demo.api.post.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.api.post.dto.PostInfoRes;
import com.example.demo.api.post.dto.PostRankRes;
import com.example.demo.api.post.dto.PostSaveReq;
import com.example.demo.api.post.dto.PostSaveRes;
import com.example.demo.api.post.entity.PostMapper;
import com.example.demo.api.post.repository.PostCustomRepository;
import com.example.demo.api.post.repository.PostRepository;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final PostCustomRepository postCustomRepository;
  private final PostMapper postMapper;

  public PostSaveRes createPost(PostSaveReq postSaveReq) {
    return postMapper.toPostSaveRes(postRepository.save(postMapper.toPostEntity(postSaveReq)));
  }

  @Transactional(readOnly = true)
  public Page<PostInfoRes> retrievePostInfoList(Pageable pageable) {
    return postRepository.findAll(pageable).map(postMapper::toPostInfoRes);
  }

  @Transactional(readOnly = true)
  public List<PostRankRes> retrievePostRankList() {
    List<PostRankRes> postRankResList = postCustomRepository.retrievePostRank();

    postRankResList
        .stream()
        .collect(Collectors.groupingBy(p -> new ImmutablePair<>(p.getPostId(), p.getPostIp())))
        .values()
        .stream();
    // .map(l -> new PostRankRes(
    // 0L,
    // l.stream
    // ));

    long rank = 1;
    for (PostRankRes p : postRankResList)
      p.setPostRank(rank++);

    return postRankResList;
  }

}
