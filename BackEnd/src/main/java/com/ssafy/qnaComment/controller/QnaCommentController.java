package com.ssafy.qnaComment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.qnaComment.model.QnaComment;
import com.ssafy.qnaComment.model.service.QnaCommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/qna/qnaComment")
public class QnaCommentController {
	private final Logger logger = LoggerFactory.getLogger(QnaCommentController.class);
	
	private QnaCommentService qnaCommentService;

	public QnaCommentController(QnaCommentService qnaCommentService) {
		super();
		this.qnaCommentService = qnaCommentService;
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "QnA 댓글 목록 조회 API", notes = "QnA 댓글 목록을 조회하는 역할을 합니다. /qna/qnaComment/list")
	public ResponseEntity<?> list() throws Exception {
		List<QnaComment> list = qnaCommentService.listQnaComment();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/view/{idx}")
	@ApiOperation(value = "QnA 댓글 상세 조회 API", notes = "QnA 댓글의 idx를 RequestParam으로 받아서 해당 QnA 댓글을 상세 조회하는 역할을 합니다. /qna/qnaComment/view")
	public ResponseEntity<?> view(@PathVariable(value="idx") int idx)
			throws Exception {
		logger.debug("view qnaComment idx : {}", idx);
		QnaComment qnaComment = qnaCommentService.viewQnaComment(idx);
		return ResponseEntity.ok(qnaComment);
	}
	
	@PostMapping("/regist")
	@ApiOperation(value = "QnA 댓글 등록 API", notes = "QnA 댓글을 등록하는 역할을 합니다. /qna/qnaComment/regist")
	public ResponseEntity<?> regist(@RequestBody QnaComment qnaComment) throws Exception {
		logger.debug("write qnaComment : {}", qnaComment);
		qnaCommentService.registQnaComment(qnaComment);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PutMapping("/modify")
	@ApiOperation(value = "QnA 댓글 수정 조회 API", notes = "QnA 댓글을 수정하는 역할을 합니다. /qna/qnaComment/modify")
	public ResponseEntity<?> modify(@RequestBody QnaComment qnaComment) throws Exception {
		logger.debug("modify qnaComment : {}", qnaComment);
		qnaCommentService.modifyQnaComment(qnaComment);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete/{idx}")
	@ApiOperation(value = "QnA 댓글 삭제 API", notes = "QnA 댓글의 idx를 PathVariable으로 받아서 QnA 댓글 삭제하는 역할을 합니다. /qna/qnaComment/delete")
	public ResponseEntity<?> delete(@PathVariable("idx") int idx)
			throws Exception {
		logger.debug("delete qna idx : {}", idx);
		qnaCommentService.deleteQnaComment(idx);
		return ResponseEntity.ok().build();
	}
}
