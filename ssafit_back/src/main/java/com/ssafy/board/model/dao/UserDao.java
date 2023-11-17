package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.User;

public interface UserDao {

	List<User> selectAll();

	int insertUser(User user);
	
	User selectOne(String userId);
	
	public void deleteUser(int userId);

	public void updateUser(User user);


	User findByEmail(String email);
	
}