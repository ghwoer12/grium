package com.project.four.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.service.GalleryService;
import com.project.four.util.AES256Util;
import com.project.four.util.Pagination;
import com.project.four.util.S3Util;

import ch.qos.logback.classic.spi.LoggerContextAwareBase;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/gallary")
public class GalleryController {

	public static final Logger logger = LoggerFactory.getLogger(GalleryController.class);


	@Autowired
	private AES256Util util;
	
	@Autowired
	private S3Util s3util;

	@Autowired
	private GalleryService gservice;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket; // 버킷이름

	@Value("${cloud.aws.s3.bucket.url}")
	private String defaultUrl; // 버킷 주소
	
	

	@PostMapping("/upload")
	public ResponseEntity<Map<String, Object>> upload(@RequestPart MultipartFile[] files, @RequestPart GalleryDto gallery){
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(">>>>파일 시작할꺼야");
		System.out.println(gallery);
		System.out.println(files);
		
		List<GaddressDto> galinfo = new ArrayList<>();

		for(MultipartFile mfile : files) {
			GaddressDto add = new GaddressDto();
			String originName = mfile.getOriginalFilename();
			
			String ext = originName.substring(originName.lastIndexOf('.')); //파일형식 
			String saveFileName = UUID.randomUUID().toString()+ext; //파일이름 암호화한거야
			String path = System.getProperty("user.dir"); //현재 backend위치
			//현재 작업하고있는 파일 디렉토리 + 암호화한 파일이름
			File file = new File(path ,saveFileName); //여기까지 convert
			//경로이름을 넣는거야 . 이 경로에 file이라는 객체를 만듣꺼야
			//file 생성시 user/dir이라는 디렉토리에 생성되도록 하기 위해서 경로를 없어오고
			
			try {
				mfile.transferTo(file); //현재 진행하는 디렉토리에 파일이 저장이 되고 
				s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
				String url  = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
				System.out.println("저장경로 :  "+ url);
				//이제 service에 db 저장하기있어야지
				file.delete(); // 업로드가 끝났으니까 로컬에 있는 파일은 삭제
				
				add.setServer_path(url); //경로
				add.setServer_photo(saveFileName); //중복을 위해 변경시켜준 이름
				add.setOrigin_photo(originName); //원본이름
				
						
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			galinfo.add(add);
		}//포문
		gallery.setGaddress(galinfo);
		gallery.setGone_id("123");
		String email = gallery.getEmail();
		try {
			email = util.encrypt(email);
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("email :: "+email); //암호화 망할
		String user_id = gservice.find_id(email);
		gallery.setUser_id(user_id);
		System.out.println("넣기전에 확인차 : >>> "+gallery);
		
		gservice.upload(gallery);
		System.out.println("끝입니다!!");
		
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		return null;
	}	
	
	
	//
	@ApiOperation(value="Photo page list", notes= "앨범 페이지 사진 목록")
	@GetMapping("/list/{page}")
	public ResponseEntity<Map<String, Object>> allList(@PathVariable int page, @RequestParam String gone_id, @RequestParam String user_id) {
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		
		int range = (page / 9) + 1;
		int listCnt = 0;
		int isOwner = 0;
		List<GalleryDto> list = null;
		
		// 상주 확인
		try {
			logger.info("====================================> 상주 확인");
			GoneDto gone = gservice.find_gone(gone_id, user_id);
			if(gone != null) isOwner = 1; // 상주이니? 
			
			// 총 게시물 개수
			listCnt = gservice.get_total(isOwner); //상주인지 확인해주는거야 상주는 비밀글도 확인할수있자나
			 
			// 페이지 처리
			logger.info("====================================> 페이징");
			Pagination pagination = new Pagination();
			pagination.setListSize(9);
			pagination.pageInfo(page, range, listCnt, isOwner);
			
			logger.info("====================================> 글 목록 받기");
			list = gservice.get_allList(pagination);
			
			// 복호화 사진은 복호화안하고 그냥 보내니까
//			for (int i = 0; i < list.size(); i++) {
//				list.get(i).setWriter(util.decrypt(list.get(i).getWriter()));
//			}
			
			resultMap.put("pagination", pagination);
			resultMap.put("list", list);
			status = HttpStatus.ACCEPTED;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("리스트업 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	
	
	
	
	

}
