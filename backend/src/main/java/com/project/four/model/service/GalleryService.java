package com.project.four.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.util.Pagination;

public interface GalleryService {
	
	void upload(GalleryDto gallary);
	String find_id(String email);
	GoneDto find_gone(String gone_id, String user_id);
	int get_total(int isOwner);
	List<GalleryDto> get_allList(Pagination pagination);
}
