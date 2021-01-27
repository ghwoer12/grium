package com.project.four.model.service;

import com.project.four.model.dto.UserDto;

public interface UserService {
	
	public int signup(UserDto user); //KY
	
	UserDto userLogin(String email, String password) throws Exception; //LS
	
	int edit(UserDto user) throws Exception; // SB
	
	public int checkOut(String passemail) throws Exception;
	
	public int withdraw(UserDto user);
}
