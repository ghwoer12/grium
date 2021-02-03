package com.project.four.model.service;

import java.util.List;

import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.util.Pagination;

public interface BoardService {

	int insert(BoardDto board) throws Exception;

	GoneDto checkchild(String gone_id, String user_id) throws Exception;

	List<BoardDto> getallList(Pagination pagination) throws Exception;

	List<BoardDto> getpartList() throws Exception;

	int getcnt(int isOwner) throws Exception;

}
