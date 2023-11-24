package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.board.model.dto.Comment;
import com.ssafy.board.model.dto.Money;
import com.ssafy.board.model.dto.User;
import com.ssafy.board.model.dto.UserDTO;
import com.ssafy.board.model.service.UserService;
import com.ssafy.board.security.JwtTokenUtil;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/api-user")
@Api(tags="사용자 컨트롤러")
public class UserRestController {
	private static final String SUCCESS="success";
	private static final String FAIL="fail";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtUtil; 
	
	@GetMapping("users")
	public List<User> userList(Model model) {
		return userService.getUserList();
	}	
	
	
	@PostMapping("signup")
	public ResponseEntity<User> signup(@RequestBody User user) {
		System.out.println(user);
	    userService.signup(user);
	    user.setPassword(null); // 민감한 정보 숨기기
	    return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/check_email")
    public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email){
        return userService.isEmailUnique(id, email)? "OK" : "Duplicated";
    }
	
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<Void> delete(@PathVariable int userId){
		userService.removeUser(userId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("update") 
	public ResponseEntity<Void> update(@RequestBody User user){
		userService.modifyUser(user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("charge") 
	public ResponseEntity<Map<String, Object>> charge(@RequestBody Money charge)throws IllegalAccessException {
		Map<String, Object> result = new HashMap<>();
	    Map<String, String> credentials = new HashMap<>();
	    credentials.put("email", charge.getEmail());
	    credentials.put("cash", charge.getMoney());
	    System.out.println(charge.getEmail());
	    System.out.println(charge.getMoney());
	    
	    ResponseEntity<UserDTO> response = userService.chargeMoney(credentials); // 수정된 메소드 호출
					
	    if (response.getStatusCode() == HttpStatus.OK) {
	    	UserDTO loginUser = response.getBody();
	        // UserServiceImpl에서 생성된 토큰을 사용
	        String token = loginUser.getToken(); 
	        System.out.println("Sending JWT Token to Client: " + token);
	        result.put("access-token", token);
	        result.put("message", SUCCESS);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } else {
	        result.put("message", FAIL);
	        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
	    }
	}

	@PutMapping("use") 
	public ResponseEntity<Map<String, Object>> use(@RequestBody Money charge)throws IllegalAccessException {
		Map<String, Object> result = new HashMap<>();
	    Map<String, String> credentials = new HashMap<>();
	    credentials.put("email", charge.getEmail());
	    credentials.put("cash", charge.getMoney());
	    
	    ResponseEntity<UserDTO> response = userService.chargeMoney(credentials); // 수정된 메소드 호출
					
	    if (response.getStatusCode() == HttpStatus.OK) {
	    	UserDTO loginUser = response.getBody();
	        // UserServiceImpl에서 생성된 토큰을 사용
	        String token = loginUser.getToken(); 
	        System.out.println("Sending JWT Token to Client: " + token);
	        result.put("access-token", token);
	        result.put("message", SUCCESS);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } else {
	        result.put("message", FAIL);
	        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
	    }
	}
	
	
//	@PostMapping("login")
//	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
//		User tmp = userService.login(user);
//		if(tmp == null)
//			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);  //로그인 페이지로 다시 튕기게 만듬
//		
//		session.setAttribute("loginUser", tmp.getNickname());
//		return new ResponseEntity<String>(tmp.getNickname(),HttpStatus.OK);
//	}
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) throws IllegalAccessException {
	    Map<String, Object> result = new HashMap<>();
	    Map<String, String> credentials = new HashMap<>();
	    credentials.put("email", user.getEmail());
	    credentials.put("password", user.getPassword());

	    ResponseEntity<UserDTO> response = userService.login(credentials); // 수정된 메소드 호출

	    if (response.getStatusCode() == HttpStatus.OK) {
	    	UserDTO loginUser = response.getBody();
	        // UserServiceImpl에서 생성된 토큰을 사용
	        String token = loginUser.getToken(); 
	        System.out.println("Sending JWT Token to Client: " + token);
	        result.put("access-token", token);
	        result.put("message", SUCCESS);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } else {
	        result.put("message", FAIL);
	        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
	    }
	}
	
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("find/{email}")
	public User findUserEmail(@PathVariable String email){
		
		return userService.findUser(email);
	}
	
	
	@GetMapping("getFollowingList/{id}")
	public ResponseEntity<?> getFollowingList(@PathVariable int userId) {
		List<User> list = userService.getFollowingList(userId);
		if(list==null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getFollowerList/{id}")
	public ResponseEntity<?> getFollowerList(@PathVariable int userId) {
		List<User> list = userService.getFollowerList(userId);
		if(list==null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
}