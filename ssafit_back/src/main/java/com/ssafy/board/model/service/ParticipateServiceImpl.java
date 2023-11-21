package com.ssafy.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.ParticipateDao;
import com.ssafy.board.model.dto.Participate;

@Service
public class ParticipateServiceImpl implements ParticipateService{
	
	@Autowired
	private ParticipateDao participateDao;

	@Override
	public void insert(Participate participate) {
		participateDao.create(participate);
	}

	@Override
	public void remove(int participateId) {
		participateDao.delete(participateId);
	}
}
