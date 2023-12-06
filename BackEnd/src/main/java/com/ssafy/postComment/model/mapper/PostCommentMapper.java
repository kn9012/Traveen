package com.ssafy.postComment.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.postComment.model.PostComment;

@Mapper
public interface PostCommentMapper {
	List<PostComment> listPostComment() throws SQLException;
	void registPostComment(PostComment postComment) throws SQLException;
	void deletePostComment(int idx) throws SQLException;
	void modifyPostComment(PostComment postComment) throws SQLException;
	PostComment viewPostComment(int idx) throws SQLException;
	PostComment getPostComment(int idx) throws SQLException;
}