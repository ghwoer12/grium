package com.project.four.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.repo.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepository brepo;

	@Override
	public int insert(BoardDto board) throws Exception {
		// TODO Auto-generated method stub
		return brepo.insert(board);
	}

	@Override
	public BoardDto overid(String bnumber) throws Exception {
		// TODO Auto-generated method stub
		return brepo.overid(bnumber);
	}

	@Override
	public GoneDto checkchild(String gone_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checkchild(gone_id, user_id);
	}
}
