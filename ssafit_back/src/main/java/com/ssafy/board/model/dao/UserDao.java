package com.ssafy.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.board.model.dto.User;

public interface UserDao {

	List<User> selectAll();

	int insertUser(User user);
	
	User selectOne(int userId);
	
	public void deleteUser(int userId);

	public void updateUser(User user);
  
	User findByEmail(String email);
  
	/**선택한 사람이 팔로우 하는 모든 사람을 보여줌*/
	public List<User> selectAllFollowing(int userId);
	/**선택한 사람을 팔로우 하는 모든 사람을 보여줌*/
	public List<User> selectAllFollower(int userId);
	
	public void chargeCash(@Param("follower")int userId, @Param("following")int money);
	
	public void useCash(@Param("follower")int userId, @Param("following")int money);
	
}