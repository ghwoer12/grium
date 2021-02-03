package com.project.four.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.repo.BoardRepository;
import com.project.four.util.Pagination;

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
	public GoneDto checkchild(String gone_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checkchild(gone_id, user_id);
	}

	@Override
	public List<BoardDto> getallList(Pagination pagination) throws Exception {
		// TODO Auto-generated method stub
		return brepo.getallList(pagination);
	}

	@Override
	public List<BoardDto> getpartList() throws Exception {
		// TODO Auto-generated method stub
		return brepo.getpartList();
	}

	@Override
	public int getcnt(int isOwner) throws Exception {
		// TODO Auto-generated method stub
		return brepo.getcnt(isOwner);
	}
}
