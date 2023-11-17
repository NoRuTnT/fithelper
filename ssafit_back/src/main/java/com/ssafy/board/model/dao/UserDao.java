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
  
  // user와 연결해서 구현해야 되는 부분
	/**선택한 사람이 팔로우 하는 모든 사람을 보여줌*/
	public List<User> selectAllFollowing(int userId);
	/**선택한 사람을 팔로우 하는 모든 사람을 보여줌*/
	public List<User> selectAllFollower(int userId);
}