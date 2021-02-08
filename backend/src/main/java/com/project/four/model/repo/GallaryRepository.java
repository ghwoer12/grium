package com.project.four.model.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.GallaryDto;

@Repository
public class GallaryRepository {
	
	@Autowired
	private SqlSession session;
	
	public void upload(GallaryDto gallary) {
		session.insert("ssafy.gallary.insert", gallary);
	}
	
	public void registfile(GallaryDto gallary) {
		session.insert("ssafy.gallary.fileregist", gallary);
	}

}