package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.Comment;
import com.ssafy.board.model.dto.User;
import com.ssafy.board.model.dto.UserDTO;
import com.ssafy.board.model.service.UserService;
import com.ssafy.board.util.JwtUtil;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api-user")
@Api(tags="사용자 컨트롤러")
public class UserRestController {
	private static final String SUCCESS="success";
	private static final String FAIL="fail";
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil; 
	
	@GetMapping("users")
	public List<User> userList(Model model) {
		return userService.getUserList();
	}	
	
	
	@PostMapping("signup")
	public ResponseEntity<User> signup(@RequestBody User user) {
	    userService.signup(user);
	    user.setPassword(null); // 민감한 정보 숨기기
	    return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/check_email")
    public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email){
        return userService.isEmailUnique(id, email)? "OK" : "Duplicated";
    }
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable int userId){
		userService.removeUser(userId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("update") 
	public ResponseEntity<Void> update(@RequestBody User user){
		userService.modifyUser(user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
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
	        String token = jwtUtil.createToken("email", loginUser.getEmail()); // JWT 토큰 생성
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
	
	
	
	
	
	
	
	
	
	
	
	
}