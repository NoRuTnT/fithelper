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

import com.ssafy.board.model.dto.Comment;
import com.ssafy.board.model.dto.SearchCondition;
import com.ssafy.board.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(tags="댓글 컨트롤러")
@CrossOrigin("*")
public class CommentRestController {

	@Autowired
	private CommentService commentService;

	//1. 목록
	@GetMapping("/comment/{boardId}")
	@ApiOperation(value="댓글 조회")
	public List<Comment> list(@PathVariable int boardId){		
		return commentService.getList(boardId);
	}
	
		
	//2. 등록
	@PostMapping("/comment")
	public ResponseEntity<Comment> write(@RequestBody Comment comment){
		commentService.writeComment(comment);
		return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
	}
	
	//3. 삭제
	@DeleteMapping("/comment/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		commentService.removeComment(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
