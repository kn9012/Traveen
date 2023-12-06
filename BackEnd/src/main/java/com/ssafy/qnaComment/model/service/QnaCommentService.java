package com.ssafy.qnaComment.model.service;

import java.util.List;

import com.ssafy.qnaComment.model.QnaComment;

public interface QnaCommentService {
	List<QnaComment> listQnaComment() throws Exception;
	void registQnaComment(QnaComment qnaComment) throws Exception;
	void deleteQnaComment(int idx) throws Exception;
	void modifyQnaComment(QnaComment qnaComment) throws Exception;
	QnaComment viewQnaComment(int idx) throws Exception;
	QnaComment getQnaComment(int idx) throws Exception;
}
