package com.project.four.model.repo;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.UserDto;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession session;

	public int insert(UserDto user) {
		int result = session.insert("ssafy.user.insert", user);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
	}


	public UserDto userLogin(String email, String password) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserDto dto;
		dto = session.selectOne("ssafy.user.login", map);
		return dto;
	}

	public int update(UserDto user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", user.getEmail());
		map.put("name", user.getName());
		map.put("password", user.getPassword());
		map.put("photo", user.getPhoto());
		map.put("phone", user.getPhone());
		int res = session.update("ssafy.user.update", map);

		return res;
	}
	
	public int checkOut(String passemail) {
	      String result = "default";
	      if(session.selectOne("ssafy.user.checkout", passemail) != null) return 0;  // 탈퇴한 회원인 경우
	      else return 1; // 탈퇴 회원이 아닌 경우
	}
	
	
	public int withdraw(UserDto user) {
		int result = session.insert("ssafy.user.withdraw", user);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
	}
	
	public UserDto overid(String id) throws Exception{
		UserDto dto;
		dto = session.selectOne("ssafy.user.overid", id);
		return dto;
	}
	
	public UserDto overemail(String email) throws Exception{
		UserDto dto;
		dto = session.selectOne("ssafy.user.overemail", email);
		return dto;
	}
	
	public UserDto findPassword(String email, String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("name", name);

        UserDto dto;
        dto = session.selectOne("ssafy.user.findpw", map);

        return dto;
	}
	
	public UserDto existGet(String email) {
		UserDto dto;
		dto = session.selectOne("ssafy.user.existGet", email);
		return dto;
	}
	
	public int newPassword(String email, String password){
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		int result = session.update("ssafy.user.newPassword", map);
		
		return result;
	}
}