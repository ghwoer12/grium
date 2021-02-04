package com.project.four.model.service;

import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;

public interface BoardService {

	int insert(BoardDto board) throws Exception;

	BoardDto overid(String bnumber) throws Exception;

	GoneDto checkchild(String gone_id, String user_id) throws Exception;

}
