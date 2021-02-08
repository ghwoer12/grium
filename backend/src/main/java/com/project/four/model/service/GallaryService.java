package com.project.four.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.four.model.dto.GallaryDto;

public interface GallaryService {
	
	void upload(GallaryDto gallary);
}
