package com.project.four.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.UserDto;
import com.project.four.model.service.BoardService;
import com.project.four.util.AES256Util;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private AES256Util util;
	
	@Autowired
	private BoardService boardservice;
	
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody BoardDto board, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		// 작성자 암호화
		try {
			System.out.println("====================================> 작성자 암호화");
			board.setWriter(util.encrypt(board.getWriter()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// DB글등록
		try {
			System.out.println("====================================> 글 등록 시작");
			int result = boardservice.insert(board);
			
			if (result == 1) {
				System.out.println("====================================> 글 등록 성공");
				resultMap.put("message", "글 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 등록에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/list")
	public ResponseEntity<Map<String, Object>> allList(@RequestBody BoardDto board, @RequestBody UserDto user, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		String gone_id = board.getGone_id();
		String user_id = user.getUser_id();
		
		// 상주 확인
		try {
			System.out.println("====================================> 상주 확인");
			GoneDto gone = boardservice.checkchild(gone_id, user_id);
			
			if(gone != null) {
				System.out.println("====================================> 상주 버전 리스트업");
				
			} else {
				System.out.println("====================================> 조문객 버전 리스트업");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// DB글등록
		try {
			System.out.println("====================================> 글 등록 시작");
			int result = boardservice.insert(board);
			
			if (result == 1) {
				System.out.println("====================================> 글 등록 성공");
				resultMap.put("message", "글 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 등록에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
