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

import com.ssafy.board.model.dto.TrainerReserve;
import com.ssafy.board.model.service.TrainerReserveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="트레이너 장소예약 컨트롤러")
@CrossOrigin("*")
public class TrainerReserveRestController {

	@Autowired
	private TrainerReserveService trainerReserveService;

	//1. 유저가목록
	@GetMapping("/trainer-reserve")
	@ApiOperation(value="장소예약 조회")
	public List<TrainerReserve> list(Model model){		
		return trainerReserveService.getList();
	}
	
	//1-2. owner가 목록
	@GetMapping("/trainer-reserve/gym")
	@ApiOperation(value="장소예약 조회")
	public List<TrainerReserve> list2(Model model){		
		return trainerReserveService.getList();
	}
	
	//2. 상세보기
	@GetMapping("/trainer-reserve/{id}")
	public ResponseEntity<TrainerReserve> detail(@PathVariable int id){
		TrainerReserve trainerReserve = trainerReserveService.getTrainerReserve(id);
		return new ResponseEntity<TrainerReserve>(trainerReserve, HttpStatus.OK);
	}
	
	//3. 등록
	@PostMapping("/trainer-reserve")
	public ResponseEntity<TrainerReserve> write(@RequestBody TrainerReserve trainerReserve){
		
		trainerReserveService.writeTrainerReserve(trainerReserve);
		return new ResponseEntity<TrainerReserve>(trainerReserve, HttpStatus.CREATED);
	}
	
	//4. 삭제
	@DeleteMapping("/trainer-reserve/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		trainerReserveService.removeTrainerReserve(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//5. 수정
	@PutMapping("/trainer-reserve") 
	public ResponseEntity<Void> update(@RequestBody TrainerReserve trainerReserve){
		trainerReserveService.modifyTrainerReserve(trainerReserve);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
