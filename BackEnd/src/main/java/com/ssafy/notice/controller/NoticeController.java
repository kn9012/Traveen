package com.ssafy.notice.controller;

import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.ssafy.notice.model.Notice;
import com.ssafy.notice.model.NoticeList;
import com.ssafy.notice.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/notice")
@Api(tags = "공지사항 API", value="Traveen")
public class NoticeController {
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "공지사항 목록 조회 API", notes = "공지사항 목록을 조회하는 역할을 합니다. /notice/list")
	public ResponseEntity<?> list(@RequestParam @ApiParam(value = "공지사항 목록을 얻기 위한 부가정보.", required = true) Map<String, String> map) throws Exception {
		logger.info("listNotice map = {}", map);
		NoticeList noticeList = noticeService.listNotice(map);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(noticeList);
	}
	
	@GetMapping("/view/{idx}")
	@ApiOperation(value = "공지사항 상세 조회 API", notes = "Notice의 idx를 RequestParam으로 받아 해당 공지사항을 상세 조회하는 역할을 합니다. /notice/view")
	public ResponseEntity<?> view(@PathVariable(value="idx") int idx)
			throws Exception {
		logger.debug("view notice idx : {}", idx);
		Notice notice = noticeService.viewNotice(idx);
		noticeService.updateHit(idx);
		return ResponseEntity.ok(notice);
	}
	
	@PostMapping("/regist")
	@ApiOperation(value = "공지사항 등록 API", notes = "공지사항을 등록하는 역할을 합니다. /notice/regist")
	public ResponseEntity<?> regist(@RequestBody Notice notice) throws Exception {
		logger.debug("write notice : {}", notice);
		noticeService.registNotice(notice);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/modify")
	@ApiOperation(value = "공지사항 수정 API", notes = "공지사항 수정하는 역할을 합니다. /notice/modify")
	public ResponseEntity<?> modify(@RequestBody Notice notice) throws Exception {
		logger.debug("modify notice : {}", notice);
		noticeService.modifyNotice(notice);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete/{idx}")
	@ApiOperation(value = "공지사항 삭제 API", notes = "Notice의 idx를 PathVariable으로 받아 공지사항을 삭제하는 역할을 합니다. /notice/delete")
	public ResponseEntity<?> delete(@PathVariable("idx") int idx)
			throws Exception {
		logger.debug("delete notice idx : {}", idx);
		noticeService.deleteNotice(idx);
		return ResponseEntity.ok().build();
	}
}