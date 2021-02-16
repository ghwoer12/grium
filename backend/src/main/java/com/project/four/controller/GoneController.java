package com.project.four.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FamilyDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;
import com.project.four.model.service.GoneService;
import com.project.four.util.AES256Util;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/gone")
public class GoneController {

	public static final Logger logger = LoggerFactory.getLogger(GoneController.class);

	@Autowired
	private AES256Util util;

	@Autowired
	private GoneService goneservice;
	
	// 고인 리스트 가져오기
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> goneList() {
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();

		try {
			logger.info("====================================> 고인 리스트 받기");
			List<GoneDto> baseList = goneservice.getList();
			System.out.println("zzzzzzzzz");

			// 오픈 여부 확인
//			logger.info("====================================> 조문관 오픈 여부 확인");
//			for (GoneDto gone : goneList) {
//				String gone_id = gone.getGone_id();
//				ProcedureDto proceduredto = goneservice.getprocedure(gone_id);
//				if(proceduredto.getOpen() == 0) {
//					goneList.remove(gone);
//				}
//			}
			
			// 상주 이름 가져와서 고인 정보와 리스트에 넣기
			List<Object> goneList = new ArrayList<Object>();
			logger.info("====================================> 상주 이름 가져오기");
			for (GoneDto gone : baseList) {
//				String gone_id = gone.getGone_id();
//				FamilyDto familydto = goneservice.getfamily(gone_id);
//				String family_nm = familydto.getFamily_nm();
				goneList.add(gone);
//				goneList.add(family_nm);
			}

			resultMap.put("goneList", goneList);
			resultMap.put("message", "고인 리스트 가져오기에 성공하였습니다.");
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("고인 리스트업 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/list/{gone_id}")
	public ResponseEntity<Map<String, Object>> getgone(@RequestParam String gone_id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("====================================> 고인 정보 가져오기");
			// 고인 정보 가져오기
			GoneDto gonedto = goneservice.getgone(gone_id);

			if (gonedto != null) {
				logger.info("====================================> 고인 정보 가져오기 성공");
				FamilyDto familydto = goneservice.getfamily(gone_id);
				ProcedureDto proceduredto = goneservice.getprocedure(gone_id);
				CondolanceDto condolancedto = goneservice.getcondolance(gone_id);
				FuneralDto funeraldto = goneservice.getfuneral(gone_id);
				List<GalleryDto> gallerydto = goneservice.getgallery(gone_id);
				
				// 복호화
//				dto.setGone_nm(util.decrypt(dto.getGone_nm()));

				resultMap.put("gone", gonedto);
				resultMap.put("family", familydto);
				resultMap.put("procedure", proceduredto);
				resultMap.put("condolance", condolancedto);
				resultMap.put("funeral", funeraldto);
				resultMap.put("gallery", gallerydto);
				resultMap.put("message", "고인 정보 가져오기에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "고인 정보 가져오기에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("고인 정보 가져오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
