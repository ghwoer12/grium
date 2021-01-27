package com.project.four.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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
import com.project.four.util.AES256Util;

@CrossOrigin(origins = {"*"}, maxAge=6000)
@RestController
@RequestMapping("/user")
public class UserController {
   
   @Autowired
   private JwtService jwtservice;
   
   @Autowired
   private UserService userservice;
   
   @Autowired
   private AES256Util util;
   
   public static final Logger logger = LoggerFactory.getLogger(UserController.class);
   
   @PostMapping("/signup")
   @ResponseBody
   public boolean signup(@RequestBody UserDto user, HttpServletResponse response, HttpSession session) {
      System.out.println("====================================> User Signup");
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;
      //userid 셋팅
      StringBuffer made = new StringBuffer();
      
      for(int i=0;i<6;i++) {
         char a = (char)((Math.random()*26) +97); //소문자
         int ann = (int)(Math.random()*9) +1; //숫자
         made.append(a); made.append(ann);
      }
      
      char b = (char)((Math.random()*26)+97);
      made.append(b);
      System.out.println(made);
      String line = made.toString();
      user.setUserid(line); // userid 셋팅끝!
      
      String email = user.getEmail();
      String password = user.getPassword();
      String name = user.getName();
      String phone = user.getPhone();
      
      try {
         email = util.encrypt(email);
         password = util.encrypt(password);
         name = util.encrypt(name);
         phone = util.encrypt(phone);
         
      } catch (UnsupportedEncodingException | GeneralSecurityException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      user.setEmail(email);
      user.setPassword(password);
      user.setName(name);
      user.setPhone(phone);
      
      System.out.println(user);
      try {
         int result = userservice.signup(user);
         
         if(result>0) {
            System.out.println("회원가입 성공");
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

       String passemail = null;
       String passpw = null;

       try {
           System.out.println("====================================> 암호화");
           passemail = util.encrypt(email);
           passpw = util.encrypt(password);

       } catch (Exception e) {
           logger.error("암호화 실패 : {}", e);
           resultMap.put("message", e.getMessage());
           status = HttpStatus.INTERNAL_SERVER_ERROR;
       }

       try {
           System.out.println("====================================> 탈퇴여부 확인");
           int result = userservice.checkOut(passemail);

           if(result == 1) {
               System.out.println("====================================> 로그인 입장");
               UserDto loginUser = userservice.userLogin(passemail, passpw);
               System.out.println(user);

               if(loginUser != null) {
                   System.out.println("성공" + loginUser.getName());
                   String token = jwtservice.create(loginUser);
                   logger.trace("로그인 토큰정보 : {}", token);

                   resultMap.put("auth-token", token);
                   resultMap.put("userid", loginUser.getUserid());
                   resultMap.put("email", loginUser.getEmail());
                   resultMap.put("password", loginUser.getPassword());
                   resultMap.put("name", loginUser.getName());
                   resultMap.put("phone", loginUser.getPhone());
                   resultMap.put("photo", loginUser.getPhoto());

                   status = HttpStatus.ACCEPTED;
               } else {
                   resultMap.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
                   status = HttpStatus.ACCEPTED;
               }
           } else {
               resultMap.put("message", "존재하지 않는 회원입니다.");
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
    	  String password = user.getPassword();
    	  String name = user.getName();
    	  String phone = user.getPhone();
    	  
    	  user.setPassword(util.encrypt(password));
    	  user.setName(util.encrypt(name));
    	  user.setPhone(util.encrypt(phone));
    	  
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
    
    
    @PostMapping("/withdraw")
	@ResponseBody
	public boolean withdraw(@RequestBody UserDto user, HttpServletResponse response, HttpSession session) {
		System.out.println("====================================> User Withdraw");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(user);
		try {
			int result = userservice.withdraw(user);
			
			if(result>0) {
				return true;
			}else {
				resultMap.put("message", "회원탈퇴 실패");
				return false;
			}
		} catch (Exception e) {
			//TODO: handle exception
			logger.error("회원탈퇴 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			return false;
		}
	}
}