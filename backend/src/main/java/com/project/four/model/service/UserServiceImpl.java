package com.project.four.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.UserDto;
import com.project.four.model.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired 
	UserRepository urepo;

	@Override
	public int signup(UserDto user) {
		// TODO Auto-generated method stub
		return urepo.insert(user);
	}
	@Override
	public UserDto userLogin(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		return urepo.userLogin(email, password);
	}
	
	@Override
	public int edit(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		return urepo.update(user);
	}
}
