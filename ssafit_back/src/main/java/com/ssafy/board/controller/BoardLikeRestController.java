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

import com.ssafy.board.model.dto.BoardLike;
import com.ssafy.board.model.service.BoardLikeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="게시판 컨트롤러")
@CrossOrigin("*")
public class BoardLikeRestController {

	@Autowired
	private BoardLikeService boardLikeService;

	//1. 좋아요 누름 (insert)
	@PostMapping("/boardLike")
	@ApiOperation(value="게시글 조회")
	public ResponseEntity<BoardLike> add(@RequestBody BoardLike boardLike){
		boardLikeService.addBoardLike(boardLike);
		return new ResponseEntity<BoardLike>(boardLike, HttpStatus.CREATED);
	}
	
	//2. 좋아요 다시 취소해 (delete)
	@DeleteMapping("/boardLike/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		boardLikeService.cancelBoardLike(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
