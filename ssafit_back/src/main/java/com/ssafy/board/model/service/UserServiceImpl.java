package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.UserDao;
import com.ssafy.board.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	
	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}


	@Override
	public User login(User user) {
		User tmp = userDao.selectOne(user.getId());
		if(tmp != null && tmp.getPassword().equals(user.getPassword()))
			return tmp;
		return null;
	}

	@Transactional
	@Override
	public void removeUser(int userId) {
		userDao.deleteUser(userId);
		
	}

	@Transactional
	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
		
	}


	@Override
	public List<User> getFollowingList(int userId) {
		return userDao.selectAllFollowing(userId);
	}


	@Override
	public List<User> getFollowerList(int userId) {
		return userDao.selectAllFollower(userId);
	}


	

}
