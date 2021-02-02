package com.project.four.model.repo;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession session;

	public int insert(BoardDto board) throws Exception{
		int result = session.insert("ssafy.board.insert", board);
		return result;
	}

	public BoardDto overid(String bnumber) throws Exception{
		BoardDto dto;
		dto = session.selectOne("ssafy.board.overid", bnumber);
		return dto;
	}

	public GoneDto checkchild(String gone_id, String user_id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gone_id", gone_id);
		map.put("user_id", user_id);
		GoneDto dto;
		dto = session.selectOne("ssafy.board.checkchild", map);
		
		return dto;
	}
}
