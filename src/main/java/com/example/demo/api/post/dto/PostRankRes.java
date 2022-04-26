package com.example.demo.api.post.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 랭킹 조회 응답 DTO")
public class PostRankRes {

  @NotNull
  @Schema(description = "순위", required = true, example = "1")
  private Long postRank;

  @NotNull
  @Schema(description = "글 개수", required = true, example = "1234")
  private Long postCount;

  @NotNull
  @Schema(description = "게시글 작성자", example = "author")
  private String postAuthor;

  @Schema(description = "게시글 작성자 아이디", example = "id")
  private String postId;

  @Schema(description = "게시글 작성자 아이피", example = "123.213")
  private String postIp;

  @NotNull
  @Schema(description = "총 댓글수", example = "1")
  private Long postCommentCount;

  @NotNull
  @Schema(description = "총 조회수", example = "1")
  private Long postGallCount;

  @NotNull
  @Schema(description = "총 추천수", example = "1")
  private Long postGallRecommend;

  public PostRankRes sum(PostRankRes p1, PostRankRes p2) {
    return new PostRankRes(
        0L,
        p1.postCount + p2.postCount,
        p1.postAuthor + ", " + p2.postAuthor,
        p1.postId,
        p1.postIp,
        p1.postCommentCount + p2.postCommentCount,
        p1.postGallCount + p2.postGallCount,
        p1.postGallRecommend + p2.postGallRecommend);
  }

}
