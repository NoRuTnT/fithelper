package com.ssafy.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.FollowDao;

@Service
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	private FollowDao followDao;

	@Override
	public void insertFollow(int follower, int following) {
		followDao.insertFollow(follower, following);
	}

	@Override
	public void deleteFollow(int follower, int following) {
		followDao.deleteFollow(follower, following);
	}
	
}
