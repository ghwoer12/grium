package com.project.four.model.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.BoardDto;

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
}
