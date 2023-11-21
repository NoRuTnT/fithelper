package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.UserReserveDao;
import com.ssafy.board.model.dto.UserReserve;



@Service
public class UserReserveServiceImpl implements UserReserveService {

	private UserReserveDao userReserveDao;
	
	@Autowired
	public void setUserReserveDao(UserReserveDao userReserveDao) {
		this.userReserveDao = userReserveDao;
	}
	
	
	@Override
	public List<UserReserve> getList() {
		return userReserveDao.userselectAll();
	}
	
	@Override
	public List<UserReserve> getList2() {
		return userReserveDao.gymselectAll();
	}

	@Transactional
	@Override
	public void writeUserReserve(UserReserve userReserve) {
		userReserveDao.insertUserReserve(userReserve);
	}

	@Override
	public UserReserve getUserReserve(int id) {
		userReserveDao.updateViewCnt(id);
		return userReserveDao.selectOne(id);
	}

	@Transactional
	@Override
	public void modifyUserReserve(UserReserve userReserve) {
		userReserveDao.updateUserReserve(userReserve);
	}

	@Transactional
	@Override
	public void removeUserReserve(int id) {
		userReserveDao.deleteUserReserve(id);
	}



}
