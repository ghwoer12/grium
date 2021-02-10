package com.project.four.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.project.four.model.dto.GallaryDto;
import com.project.four.model.repo.GallaryRepository;

@Service
public class GallaryServiceImpl implements GallaryService{
	
	@Autowired
	GallaryRepository grepo;
	
	@Override
	@Transactional
	public void upload(List<GallaryDto> dtoList) {
		// TODO Auto-generated method stub
		for(GallaryDto dto : dtoList) {
			grepo.upload(dto);
		}
		
	}
	
	/*
	@Value("${cloud.aws.s3.bucket}")
	private String bucket; //버킷 이름
	
	@Value("${cloud.aws.s3.bucket.url}")
	private String defaultUrl; //버킷 주소
	
	
	private final AmazonS3Client amazonS3Client;
	//생정자 의존성 주입! s3 전송객체를 만들 때 필요한 클래스! 클래스 생성자를 통해서 의존성 주입!
	public GallaryServiceImpl(AmazonS3Client amazonS3Client) {
		// TODO Auto-generated constructor stub
		this.amazonS3Client = amazonS3Client;
	}
	
	@Override
	public String upload(MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		//파일 올린 파일의 원래 이름
		String originName = uploadFile.getOriginalFilename();
		String url;
		
		try {
			final String ext = originName.substring(originName.lastIndexOf('.'));
			final String saveFileName = UUID.randomUUID().toString()+ext; //변경한 파일이름이지
			
			File file = new File(System.getProperty("user.dir")+saveFileName);
			
			uploadFile.transferTo(file);
			
			uploadOnS3(saveFileName, file);
			
			url = defaultUrl+saveFileName;
			
			file.delete();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			url = null;
			e.printStackTrace();
		}
		
		
		
		return url;
	}
	
	private void uploadOnS3(final String findName, final File file) {
		final TransferManager tranfermanager = new TransferManager(this.amazonS3Client);
		
		final PutObjectRequest request = new PutObjectRequest(bucket, findName, file);
		
		final Upload upload = tranfermanager.upload(request);
		
		try {
			upload.waitForCompletion();
		} catch (AmazonClientException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/

}
