package com.ssafy.board.model.service;

import com.ssafy.board.model.dto.Participate;

public interface ParticipateService {
	/**방에 가입함*/
	void insert(Participate participate);
	/**방 탈출*/
	void remove(int participateId);
}
