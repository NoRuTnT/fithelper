package com.ssafy.board.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.UserReserve;
import com.ssafy.board.model.service.UserReserveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="사용자 장소예약 컨트롤러")
@CrossOrigin("*")
public class UserReserveRestController {

	@Autowired
	private UserReserveService userReserveService;

	//1. 유저가목록
	@GetMapping("/user-reserve")
	@ApiOperation(value="장소예약 조회")
	public List<UserReserve> list(Model model){		
		return userReserveService.getList();
	}
	
	//1-2. owner가 목록
	@GetMapping("/user-reserve/gym")
	@ApiOperation(value="장소예약 조회")
	public List<UserReserve> list2(Model model){		
		return userReserveService.getList();
	}
	
	//2. 상세보기
	@GetMapping("/user-reserve/{id}")
	public ResponseEntity<UserReserve> detail(@PathVariable int id){
		UserReserve userReserve = userReserveService.getUserReserve(id);
		return new ResponseEntity<UserReserve>(userReserve, HttpStatus.OK);
	}
	
	//3. 등록
	@PostMapping("/user-reserve")
	public ResponseEntity<UserReserve> write(@RequestBody UserReserve userReserve){
		
		userReserveService.writeUserReserve(userReserve);
		return new ResponseEntity<UserReserve>(userReserve, HttpStatus.CREATED);
	}
	
	//4. 삭제
	@DeleteMapping("/user-reserve/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		userReserveService.removeUserReserve(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//5. 수정
	@PutMapping("/user-reserve") 
	public ResponseEntity<Void> update(@RequestBody UserReserve userReserve){
		userReserveService.modifyUserReserve(userReserve);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
