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

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;
import com.ssafy.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="게시판 컨트롤러")
@CrossOrigin("*")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	//1. 목록
	@GetMapping("/board")
	@ApiOperation(value="게시글 조회")
	public ResponseEntity<?> list(SearchCondition condition){
		List<Board> list = boardService.search(condition); 
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	//2. 상세보기
	@GetMapping("/board/{id}")
	public ResponseEntity<Board> detail(@PathVariable int id){
		Board board = boardService.getBoard(id);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	//3. 등록
	@PostMapping("/board")
	public ResponseEntity<Board> write(@RequestBody Board board){
		
		boardService.writeBoard(board);
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}
	
	//4. 삭제
	@DeleteMapping("/board/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		boardService.removeBoard(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//5. 수정
	@PutMapping("/board") 
	public ResponseEntity<Void> update(@RequestBody Board board){
		boardService.modifyBoard(board);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//6. 좋아요 증가/감소
	@PutMapping("/board/{id}/{type}") 
	public ResponseEntity<Void> updateLikeCnt(@PathVariable int id, @PathVariable int type){
		// url 변수 값이 1이면 증가
		if(type==1) {
			boardService.updateLikeCntUp(id);
		}else { 
			boardService.updateLikeCntDown(id);// 0이면 감소
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
