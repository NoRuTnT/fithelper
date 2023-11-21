package com.ssafy.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.Participate;
import com.ssafy.board.model.service.ParticipateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="class 참가(participate) 컨트롤러")
@CrossOrigin("*")
public class ParticipateRestController {
	
	@Autowired
	private ParticipateService participateService;
	
	// 1. 일반 사용자가 class에 들어올 때
	@PostMapping("/participate")
	@ApiOperation(value="class 들어옴")
	public ResponseEntity<Void> add(@RequestBody Participate participate){
		participateService.insert(participate);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	// 2. 일반 사용자가 class를 나갈 때
	@DeleteMapping("/participate")
	@ApiOperation(value="class 나감")
	public ResponseEntity<Void> delete(@PathVariable int participateId){
		participateService.remove(participateId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
