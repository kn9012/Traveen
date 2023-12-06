package com.ssafy.qnaComment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.qnaComment.model.QnaComment;
import com.ssafy.qnaComment.model.mapper.QnaCommentMapper;

@Service
public class QnaCommentServiceImpl implements QnaCommentService {
	private QnaCommentMapper qnaCommentMapper;

	public QnaCommentServiceImpl(QnaCommentMapper qnaCommentMapper) {
		super();
		this.qnaCommentMapper = qnaCommentMapper;
	}
	
	@Override
	public List<QnaComment> listQnaComment() throws Exception {
		return qnaCommentMapper.listQnaComment();
	}

	@Override
	public void registQnaComment(QnaComment qnaComment) throws Exception {
		qnaCommentMapper.registQnaComment(qnaComment);
	}

	@Override
	public void deleteQnaComment(int idx) throws Exception {
		qnaCommentMapper.deleteQnaComment(idx);
	}

	@Override
	public void modifyQnaComment(QnaComment qnaComment) throws Exception {
		qnaCommentMapper.modifyQnaComment(qnaComment);
	}

	@Override
	public QnaComment viewQnaComment(int idx) throws Exception {
		return qnaCommentMapper.viewQnaComment(idx);
	}

	@Override
	public QnaComment getQnaComment(int idx) throws Exception {
		return qnaCommentMapper.getQnaComment(idx);
	}
}