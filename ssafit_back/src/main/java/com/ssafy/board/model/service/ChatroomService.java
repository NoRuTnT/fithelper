package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Chatroom;

public interface ChatroomService {
	/**채팅방 생성, 일반유저와 트레이너유저 모두 가능*/
	void writeChatroom(Chatroom chatroom);
	/**일반 사용자가 전체 채팅 list 열람*/
	List<Chatroom> userChatroomList(int userId);
	/**트레이너 사용자가 전체 채팅 list 열람*/
	List<Chatroom> trainerChatroomList(int trainerId);
	/**선택한 채팅 내용 한 개 열람*/
	Chatroom chatroomDetail(int chatmsgId);
	/**작성글 삭제*/
	void removeChatroom(int chatmsgId);
}

