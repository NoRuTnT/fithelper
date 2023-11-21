package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Chatroom;

public interface ChatroomDao {
	/**채팅방 생성, 일반유저와 트레이너유저 모두 가능*/
	public void create(Chatroom chatroom);
	/**일반 사용자가 전체 채팅 list 열람*/
	public List<Chatroom> userSelectAll(int userId);
	/**트레이너 사용자가 전체 채팅 list 열람*/
	public List<Chatroom> trainerSelectAll(int trainerId);
	/**선택한 채팅 내용 한 개 열람*/
	public Chatroom selectOne(int chatmsgId);
	/**작성글 삭제*/
	public void delete(int chatmsgId);
	/**조회 했을 때 읽음 표시*/
	public void checkReadMark(int chatmsgId);
}
