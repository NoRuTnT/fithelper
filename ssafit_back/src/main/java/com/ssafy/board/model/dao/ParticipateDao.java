package com.ssafy.board.model.dao;

import com.ssafy.board.model.dto.Participate;

public interface ParticipateDao {
	/**방에 가입함*/
	public void create(Participate participate);
	/**방 탈출*/
	public void delete(int participateId);
}
