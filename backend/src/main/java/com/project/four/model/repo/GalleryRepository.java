package com.project.four.model.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.util.Pagination;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession session;
	
	public void upload(GalleryDto gallery) {
		session.insert("ssafy.gallery.insert", gallery);
	}
	
	public void registfile(GalleryDto gallery) {
		session.insert("ssafy.gallery.fileregist", gallery);
	}
	
	public String find_id(String email) {
		String re = session.selectOne("ssafy.gallery.findid", email);
		System.out.println("reposi : re : >> "+ re);
		return re;
	}
	
	public GoneDto find_gone(String gone_id, String user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gone_id", gone_id);
		map.put("user_id", user_id);
		return session.selectOne("ssafy.gallery.findgone", map);
	}
	
	public int get_total(int isOwner) {
		return session.selectOne("ssafy.gallery.getCnt", isOwner);
	}
	
	public List<GalleryDto> get_allList(Pagination pagination){
		List<GalleryDto> list = null;
		if(session.selectList("ssafy.gallery.allList", pagination) != null) list = session.selectList("ssafy.gallery.allList", pagination);
		return list;
	}

}