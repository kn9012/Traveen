package com.ssafy.postComment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.postComment.model.PostComment;
import com.ssafy.postComment.model.mapper.PostCommentMapper;

@Service
public class PostCommentServiceImpl implements PostCommentService {
	private PostCommentMapper postCommentMapper;

	public PostCommentServiceImpl(PostCommentMapper postCommentMapper) {
		super();
		this.postCommentMapper = postCommentMapper;
	}

	@Override
	public List<PostComment> listPostComment(int postIdx) throws Exception {
		return postCommentMapper.listPostComment(postIdx);
	}

	@Override
	public List<PostComment> listPostCommentByUserIdx(int userIdx) throws Exception {
		return postCommentMapper.listPostCommentByUserIdx(userIdx);
	}

	@Override
	public void registPostComment(PostComment postComment) throws Exception {
		postCommentMapper.registPostComment(postComment);
	}

	@Override
	public void deletePostComment(int idx) throws Exception {
		postCommentMapper.deletePostComment(idx);
	}

	@Override
	public PostComment viewPostComment(int idx) throws Exception {
		return postCommentMapper.viewPostComment(idx);
	}

	@Override
	public PostComment getPostComment(int idx) throws Exception {
		return postCommentMapper.getPostComment(idx);
	}
}