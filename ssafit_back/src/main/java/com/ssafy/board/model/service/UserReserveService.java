package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.UserReserve;


public interface UserReserveService {
	List<UserReserve> getList();
	
	List<UserReserve> getList2();

	void writeUserReserve(UserReserve userReserve);

	UserReserve getUserReserve(int id);

	void modifyUserReserve(UserReserve userReserve);

	void removeUserReserve(int id);

}

