package com.project.four.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.four.model.dto.UserDto;
import com.project.four.model.service.JwtService;
import com.project.four.model.service.UserService;

@CrossOrigin(origins = {"*"}, maxAge=6000)
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private JwtService jwtservice;
	
	@Autowired
	private UserService userservice;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/signup")
	@ResponseBody
	public boolean signup(@RequestBody UserDto user, HttpServletResponse response, HttpSession session) {
		System.out.println("====================================> User Signup");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(user);
		try {
			int result = userservice.signup(user);
			
			if(result>0) {
				return true;
			}else {
				resultMap.put("message", "회원가입 실패");
				return false;
			}
		} catch (Exception e) {
			//TODO: handle exception
			logger.error("회원가입 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			return false;
		}
	}

	@PostMapping("/confirm/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto user, HttpServletResponse response, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String email = user.getEmail();
        String password = user.getPassword();
      
        try {
        	System.out.println("====================================> 로그인 입장");
        	UserDto loginUser = userservice.userLogin(email, password);
            System.out.println(user);

            if(loginUser != null) {
            	System.out.println("성공" + loginUser.getName());
                String token = jwtservice.create(loginUser);
                logger.trace("로그인 토큰정보 : {}", token);

                resultMap.put("auth-token", token);
                resultMap.put("userid", loginUser.getEmail());
                resultMap.put("password", loginUser.getPassword());
                resultMap.put("name", loginUser.getName());
                resultMap.put("phone", loginUser.getPhone());
                resultMap.put("photo", loginUser.getPhoto());

                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PutMapping("/edit")
	public boolean edit(@RequestBody UserDto user, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println("edit>>>" + user);
		try {
			int result = userservice.edit(user);
			
			if(result > 0) {
				return true;
			} else {
				resultMap.put("message", "수정 실패");
				return false;
			}
		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			return false;
		}
    }
    
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        System.out.println(">>>>>> " + jwtservice.get(req.getHeader("auth-token")));
        try {
            resultMap.putAll(jwtservice.get(req.getHeader("auth-token")));
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            logger.error("정보조회 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
