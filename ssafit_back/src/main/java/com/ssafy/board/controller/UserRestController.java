package com.ssafy.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.ssafy.board.model.service.UserService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api-user")
@Api(tags="사용자 컨트롤러")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public List<User> userList(Model model) {
		return userService.getUserList();
	}
	
		
	
	
	@PostMapping("signup")
	public ResponseEntity<Integer> signup(@RequestBody User user) {
		int result = userService.signup(user);
		
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
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
	
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User tmp = userService.login(user);
		if(tmp == null)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);  //로그인 페이지로 다시 튕기게 만듬
		
		session.setAttribute("loginUser", tmp.getNickname());
		return new ResponseEntity<String>(tmp.getNickname(),HttpStatus.OK);
	}
	
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
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