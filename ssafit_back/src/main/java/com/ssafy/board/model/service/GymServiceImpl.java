package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.GymDao;
import com.ssafy.board.model.dto.Gym;
import com.ssafy.board.model.dto.SearchCondition;


@Service
public class GymServiceImpl implements GymService {

	private GymDao gymDao;
	
	@Autowired
	public void setGymDao(GymDao gymDao) {
		this.gymDao = gymDao;
	}
	
	
	@Override
	public List<Gym> getList() {
		return gymDao.selectAll();
	}

	@Transactional
	@Override
	public void writeGym(Gym gym) {
		gymDao.insertGym(gym);
	}

	@Override
	public Gym getGym(int id) {
		gymDao.updateViewCnt(id);
		return gymDao.selectOne(id);
	}

	@Transactional
	@Override
	public void modifyGym(Gym gym) {
		gymDao.updateGym(gym);
	}

	@Transactional
	@Override
	public void removeGym(int id) {
		gymDao.deleteGym(id);
	}


	@Override
	public List<Gym> search(SearchCondition condition) {
		return gymDao.search(condition);
	}


	@Override
	public void updateLikeCntUp(int gymId) {
		gymDao.updateLikeCntUp(gymId);
	}


	@Override
	public void updateLikeCntDown(int gymId) {
		gymDao.updateLikeCntDown(gymId);
	}

}
