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

import com.ssafy.board.model.dto.GymLike;
import com.ssafy.board.model.service.GymLikeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="운동장소좋아요 컨트롤러")
@CrossOrigin("*")
public class GymLikeRestController {

	@Autowired
	private GymLikeService gymLikeService;

	//1. 좋아요 누름 (insert)
	@PostMapping("/gymLike")
	@ApiOperation(value="게시글 조회")
	public ResponseEntity<GymLike> add(@RequestBody GymLike gymLike){
		gymLikeService.addGymLike(gymLike);
		return new ResponseEntity<GymLike>(gymLike, HttpStatus.CREATED);
	}
	
	//2. 좋아요 다시 취소해 (delete)
	@DeleteMapping("/gymLike/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		gymLikeService.cancelGymLike(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
