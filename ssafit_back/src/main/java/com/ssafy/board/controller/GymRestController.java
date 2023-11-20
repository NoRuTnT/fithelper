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

	//1. 목록
	@GetMapping("/gym")
	@ApiOperation(value="장소 조회")
	public ResponseEntity<?> list(SearchCondition condition){
		List<Gym> list = gymService.search(condition); 
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Gym>>(list, HttpStatus.OK);
	}
	
	//2. 상세보기
	@GetMapping("/gym/{id}")
	public ResponseEntity<Gym> detail(@PathVariable int id){
		Gym gym = gymService.getGym(id);
		return new ResponseEntity<Gym>(gym, HttpStatus.OK);
	}
	
	//3. 등록
	@PostMapping("/gym")
	public ResponseEntity<Gym> write(@RequestBody Gym gym){
		
		gymService.writeGym(gym);
		return new ResponseEntity<Gym>(gym, HttpStatus.CREATED);
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
	
	//6. 좋아요 증가/감소
	@PutMapping("/gym/{id}/{type}") 
	public ResponseEntity<Void> updateLikeCnt(@PathVariable int id, @PathVariable int type){
		// url 변수 값이 1이면 증가
		if(type==1) {
			gymService.updateLikeCntUp(id);
		}else { 
			gymService.updateLikeCntDown(id);// 0이면 감소
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
