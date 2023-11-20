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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.Chatroom;
import com.ssafy.board.model.service.ChatroomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "1:1채팅방 컨트롤러")
@CrossOrigin("*")
public class ChatroomRestController {

	@Autowired
	private ChatroomService chatroomService;

	// 1. 채팅글 작성
	@PostMapping("/chatroom")
	@ApiOperation(value = "채팅글 작성")
	public ResponseEntity<Void> writeChatroom(@RequestBody Chatroom chatroom) {
		chatroomService.writeChatroom(chatroom);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 2. 사용자의 전체 채팅 내용 확인
	@GetMapping("/userChatroomList/{userId}")
	@ApiOperation(value = "사용자의 전체 채팅 내용 확인")
	public ResponseEntity<?> userChatroomList(@PathVariable int userId) {
		List<Chatroom> list = chatroomService.userChatroomList(userId);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Chatroom>>(list, HttpStatus.OK);
	}

	// 3. 트레이너의 전체 채팅 내용 확인
	@GetMapping("/trainerChatroomList/{trainerId}")
	@ApiOperation(value = "트레이너의 전체 채팅 내용 확인")
	public ResponseEntity<?> trainerChatroomList(@PathVariable int trainerId) {
		List<Chatroom> list = chatroomService.userChatroomList(trainerId);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Chatroom>>(list, HttpStatus.OK);
	}

	// 4. 채팅 내용 상세 보기
	@GetMapping("/chatroomDetail/{chatmsgId}")
	@ApiOperation(value = "채팅 내용 상세 보기")
	public ResponseEntity<?> chatroomDetail(@PathVariable int chatmsgId) {
		Chatroom chatroom = chatroomService.chatroomDetail(chatmsgId);
		return new ResponseEntity<Chatroom>(chatroom, HttpStatus.OK);
	}
	
	// 5. 채팅 글 삭제하기 
	@DeleteMapping("/removeChatroom/{chatmsgId}")
	@ApiOperation(value = "채팅 글 삭제하기")
	public ResponseEntity<?> removeChatroom(@PathVariable int chatmsgId) {
		chatroomService.removeChatroom(chatmsgId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
