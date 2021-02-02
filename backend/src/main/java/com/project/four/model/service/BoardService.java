package com.project.four.model.service;

import com.project.four.model.dto.BoardDto;

public interface BoardService {

	int insert(BoardDto board) throws Exception;

	BoardDto overid(String bnumber) throws Exception;

}
