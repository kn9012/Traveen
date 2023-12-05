package com.ssafy.post.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.course.model.Courseinfo;
import com.ssafy.post.model.PostItem;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.course.model.Course;
import com.ssafy.post.model.Post;

@Mapper
public interface PostMapper {
	List<Post> listPost(Map<String, Object> map) throws SQLException;
	int registPost(Post post) throws SQLException;
	void registPostItem(Map<String, Object> map) throws SQLException;
	void updateCourseFlag(String courseIdx) throws SQLException;
	int modifyPost(Post post) throws SQLException;
	void modifyPostItem(Map<String, Object> map) throws SQLException;
	void deletePost(int idx) throws SQLException;

	Post viewPost(int idx) throws SQLException;
	List<PostItem> viewPostItem(int idx) throws SQLException;
	List<PostItem> listPostItem(String courseIdx) throws SQLException;
	Post getPost(int idx) throws SQLException;

	int getTotalPostCount(Map<String, Object> param) throws SQLException;
	void updateHit(int idx) throws SQLException;
}