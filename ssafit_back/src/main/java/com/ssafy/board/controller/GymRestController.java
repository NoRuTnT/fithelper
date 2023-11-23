package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.Gym;
import com.ssafy.board.model.dto.SearchCondition;
import com.ssafy.board.model.service.GymService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="장소 컨트롤러")
@CrossOrigin("*")
public class GymRestController {

	@Autowired
	private GymService gymService;

	//1. 목록보기
	@GetMapping("/gym/list/{userId}")
	@ApiOperation(value="목록 조회")
	public ResponseEntity<?> list(@PathVariable int userId){
		List<Gym> list = gymService.getList(userId);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Gym>>(list, HttpStatus.OK);
	}
	
	//2. 상세보기
	@GetMapping("/gym/{gymId}")
	public ResponseEntity<Gym> detail(@PathVariable int gymId){
		Gym gym = gymService.getGym(gymId);
		return new ResponseEntity<Gym>(gym, HttpStatus.OK);
	}
	
	//3. 등록
	@PostMapping("/gym")
	public ResponseEntity<Void> write(@RequestBody Gym gym){
		gymService.writeGym(gym);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//4. 삭제
	@DeleteMapping("/gym/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		gymService.removeGym(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//5. 수정
	@PutMapping("/gym") 
	public ResponseEntity<Void> update(@RequestBody Gym gym){
		gymService.modifyGym(gym);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
