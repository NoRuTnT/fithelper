package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.ChatroomDao;
import com.ssafy.board.model.dto.Chatroom;

@Service
public class ChatroomServiceImpl implements ChatroomService{

	@Autowired
	private ChatroomDao chatroomDao;
	
	@Override
	public void writeChatroom(Chatroom chatroom) {
		chatroomDao.create(chatroom);
	}

	@Override
	public List<Chatroom> userChatroomList(int userId) {
		return chatroomDao.userSelectAll(userId);
	}

	@Override
	public List<Chatroom> trainerChatroomList(int trainerId) {
		return chatroomDao.trainerSelectAll(trainerId);
	}

	@Override
	public Chatroom chatroomDetail(int chatmsgId) {
		chatroomDao.checkReadMark(chatmsgId);
		return chatroomDao.selectOne(chatmsgId);
	}

	@Override
	public void removeChatroom(int chatmsgId) {
		chatroomDao.delete(chatmsgId);
	}
	
	
}
