package com.ssafy.post.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.post.model.Post;
import com.ssafy.post.model.PostItem;
import com.ssafy.post.model.PostList;

public interface PostService {
	PostList listPost(Map<String, String> map) throws Exception;
	void registPost(Map<String, Object> map) throws Exception;
	void modifyPost(Map<String, Object> map) throws Exception;
	void deletePost(int idx) throws Exception;
	Post viewPost(int idx) throws Exception;
	List<PostItem> viewPostItem(int idx) throws Exception;
	Post getPost(int idx) throws Exception;
	void updateHit(int idx) throws Exception;
}
