package com.project.four.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.service.AlertService;
import com.project.four.util.AES256Util;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value = "/alert")
public class AlertController {
public static final Logger logger = LoggerFactory.getLogger(AlertController.class);
	
	@Autowired
	private AES256Util util;
	
	@Autowired
	private AlertService alertService;
	
	@ApiOperation(value="Get Alarm", notes="알람 목록 부르기")
	@GetMapping("/getalarm")
	public ResponseEntity<Map<String, Object>> getalarm(@RequestParam String user_id) {
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		List<String> g_list = null;
		List<AlertDto> list = null;
		List<BoardDto> blist = new LinkedList<BoardDto>();
		List<GalleryDto> plist = new LinkedList<GalleryDto>();
		List<GaddressDto> gadrlist = new LinkedList<GaddressDto>();
		
		try {
			logger.info("====================================> 고인 아이디 찾기");
			g_list = alertService.getgone(user_id);
			
			for (int i = 0; i < g_list.size(); i++) {
				String gone_id = g_list.get(i);
				list = alertService.getalarm(gone_id);
				
				if(list.size() != 0) {
					logger.info("====================================> 알람 목록 부르기");
					for (int j = 0; j < list.size(); j++) {
						AlertDto dto = list.get(j);
						if (dto.getBoard_id() != 0) {
							BoardDto bdto = alertService.getaboard(dto.getBoard_id());
							blist.add(bdto);
						} else if(dto.getPhoto_id() != 0) {
							GalleryDto gdto = alertService.getagallary(dto.getPhoto_id());
							plist.add(gdto);
						}
					}
				}
			}
			
			resultMap.put("blist", blist);
			resultMap.put("plist", plist);
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("알람 목록 부르기 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
