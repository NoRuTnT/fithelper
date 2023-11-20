package com.ssafy.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
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

import com.ssafy.board.model.dto.Gymowner;
import com.ssafy.board.model.service.GymownerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api-gymowner")
@Api(tags="gymowner 컨트롤러")
public class GymownerRestController {
	@Autowired
	private GymownerService gymownerService;
	
	@GetMapping("gymowners")
	public List<Gymowner> gymownerList(Model model){
		return gymownerService.getGymownerList();
	}
	
	@PostMapping("signup")
	public ResponseEntity<Gymowner> signup(@RequestBody Gymowner gymowner) {
		gymownerService.signup(gymowner);
		gymowner.setPassword(null); // 민감한 정보 숨기기
	    return new ResponseEntity<>(gymowner, HttpStatus.CREATED);
	}
	
	@PostMapping("/gymowner/check_email")
    public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email){
        return gymownerService.isEmailUnique(id, email)? "OK" : "Duplicated";
    }
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable int gymownerId){
		gymownerService.removeGymowner(gymownerId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("update") 
	public ResponseEntity<Void> update(@RequestBody Gymowner gymowner){
		gymownerService.modifyGymowner(gymowner);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Gymowner gymowner, HttpSession session) {
		Gymowner tmp = gymownerService.login(gymowner);
		if(tmp == null)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);  //로그인 페이지로 다시 튕기게 만듬
		
		session.setAttribute("loginGymowner", tmp.getNickname());
		return new ResponseEntity<String>(tmp.getNickname(),HttpStatus.OK);
	}
	
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
