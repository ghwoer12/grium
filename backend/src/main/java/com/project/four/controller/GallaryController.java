package com.project.four.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.project.four.model.dto.GallaryDto;
import com.project.four.model.service.GallaryService;
import com.project.four.util.AES256Util;
import com.project.four.util.S3Util;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/gallary")
public class GallaryController {

	@Autowired
	private AES256Util util;
	
	@Autowired
	private S3Util s3util;

	@Autowired
	private GallaryService gservice;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket; // 버킷이름

	@Value("${cloud.aws.s3.bucket.url}")
	private String defaultUrl; // 버킷 주소

	private final AmazonS3Client amazonS3Client;
	

	public GallaryController(AmazonS3Client amazonS3Client) {
		this.amazonS3Client = amazonS3Client;
	}

	
//	public String regist(MultipartFile file) {
//		String fileName = file.getOriginalFilename();
//		
//		try {
//			//putObject 업로드하는 메소드
//			//putObject ( PutObejctReqeust / String, String ,file ) 2가지 방법존재
//			//String bucketName, String key, File file 이 방법써볼께
//			//bucketname이랑 key는 bucket안에 저장될 경로를의미해 (bucket안에 우리가 디렉토리 구조를 만들수있어)
//			//key는 그 경로를 말하는거야 file은 우리가 생성한 file을 의미하고!
//			s3util.setS3Client().putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
//					.withCannedAcl(CannedAccessControlList.PublicRead)); //외부에 공개할 사진이여서 public read를 추가한거래
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return s3util.setS3Client().getUrl(bucket, fileName).toString(); //저장한 url 가져오는거야 db 저장용
//		
//		
//	}
	
	
	

	@PostMapping("/upload")
	public ResponseEntity<Map<String, Object>> upload(@RequestBody GallaryDto gallary, @RequestParam("upfile") MultipartFile[] files){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		List<GaddressDto> galinfo = new ArrayList<>();
		GallaryDto dto = new GallaryDto();
		for(MultipartFile mfile : files) {
			GaddressDto add = new GaddressDto();
			String originName = mfile.getOriginalFilename();
			
			String url;
			final String ext = originName.substring(originName.lastIndexOf('.'));
			String saveFileName = UUID.randomUUID().toString()+ext; //파일이름 암호화한거야
			String path = System.getProperty("user.dir")+saveFileName;
			//현재 작업하고있는 파일 디렉토리 + 암호화한 파일이름
			File file = new File(path); //여기까지 convert
			//경로이름을 넣는거야 . 이 경로에 file이라는 객체를 만듣꺼야
			//file 생성시 user/dir이라는 디렉토리에 생성되도록 하기 위해서 경로를 없어오고
			
			
			System.out.println("File : "+ file);
			System.out.println("System.getProperty(\"user.dir\") : "+System.getProperty("user.dir"));
			//현재 작업하고있는 파일 디렉토리래
			System.out.println("path : "+ path);
			
			add.setOrigin_photo(originName); //원본이름
			add.setServer_photo(saveFileName); //서버에 올라간 이름
			add.setServer_path(path); //path인데 이게 주소가 뒤에 파일이름까지 올라가는지 모르게센
			
			
			try {
				s3util.setS3Client().putObject(new PutObjectRequest(bucket, path, file).withCannedAcl(CannedAccessControlList.PublicRead));
				mfile.transferTo(file); //현재 진행하는 디렉토리에 파일이 저장이 되고 
				s3util.setS3Client().putObject(bucket, path, file); //업로드
				//업로드했는데 경로가 투명해 그럼면 path에 앞에 /이걸로 시작한거야 지워주면돼
				//path 경로에 있는 객체에 file을 넣어준거야 
				url = defaultUrl + path;
				//이제 service에 db 저장하기있어야지
				System.out.println(">>url > " + url);
				galinfo.add(add);
				file.delete(); // 업로드가 끝났으니까 파일을 삭제하나봐
				
						
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		gallary.setGaddress(galinfo);
		
		gservice.upload(gallary);
		
		
		
		
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

}
