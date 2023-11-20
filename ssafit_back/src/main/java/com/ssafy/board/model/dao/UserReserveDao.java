package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.UserReserve;


public interface UserReserveDao {
	public List<UserReserve> userselectAll();
	
	public List<UserReserve> gymselectAll();

	public UserReserve selectOne(int id);

	public void insertUserReserve(UserReserve userReserve);

	public void deleteUserReserve(int id);

	public void updateUserReserve(UserReserve userReserve);

	public void updateViewCnt(int id);

	
}
