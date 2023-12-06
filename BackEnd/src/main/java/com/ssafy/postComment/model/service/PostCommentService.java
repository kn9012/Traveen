package com.ssafy.postComment.model.service;

import java.util.List;

import com.ssafy.postComment.model.PostComment;

public interface PostCommentService {
	List<PostComment> listPostComment() throws Exception;
	void registPostComment(PostComment postComment) throws Exception;
	void deletePostComment(int idx) throws Exception;
	PostComment viewPostComment(int idx) throws Exception;
	PostComment getPostComment(int idx) throws Exception;
}