package com.ssafy.board.model.dto;

import java.sql.Timestamp;

public class Chatroom {
	private int chatmsgId; // 메시지 번호(식별자)
	private int trainerId; // 트레이너 번호
	private int userId; // 일반 사용자 번호
	private String message; // 메시지 내용
	private int read_or_not; // 읽었는지 여부 (0:안읽음 / 1:읽음)
	private Timestamp createDate; // 작성시간
	
	public Chatroom() {
	}

	public Chatroom(int chatmsgId, int trainerId, int userId, String message, int read_or_not, Timestamp createDate) {
		super();
		this.chatmsgId = chatmsgId;
		this.trainerId = trainerId;
		this.userId = userId;
		this.message = message;
		this.read_or_not = read_or_not;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Chatroom [chatmsgId=" + chatmsgId + ", trainerId=" + trainerId + ", userId=" + userId + ", message="
				+ message + ", read_or_not=" + read_or_not + ", createDate=" + createDate + "]";
	}

	
	
}
