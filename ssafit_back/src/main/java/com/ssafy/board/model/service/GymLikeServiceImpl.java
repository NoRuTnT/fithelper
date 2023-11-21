package com.ssafy.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.GymLikeDao;
import com.ssafy.board.model.dto.GymLike;

@Service
public class GymLikeServiceImpl implements GymLikeService{
	
	@Autowired
	private GymLikeDao gymLikeDao;

	@Override
	public void addGymLike(GymLike gymLike) {
		gymLikeDao.insertGymLike(gymLike);
	}

	@Override
	public void cancelGymLike(int userId) {
		gymLikeDao.deleteGymLike(userId);
	}

}
