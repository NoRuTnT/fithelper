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
	@Autowired
	private GymDao gymDao;

	@Override
	public void writeGym(Gym gym) {
		gymDao.insertGym(gym);
	}

	@Override
	public List<Gym> getList(int userId) {
		return gymDao.selectGymList(userId);
	}

	@Override
	public Gym getGym(int gymId) {
		return gymDao.selectOne(gymId);
	}

	@Override
	public void modifyGym(Gym gym) {
		gymDao.updateGym(gym);
	}

	@Override
	public void removeGym(int gymId) {
		gymDao.deleteGym(gymId);
	}

}
