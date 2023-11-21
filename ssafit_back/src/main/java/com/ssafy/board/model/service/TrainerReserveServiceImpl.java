package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.TrainerReserveDao;
import com.ssafy.board.model.dto.TrainerReserve;



@Service
public class TrainerReserveServiceImpl implements TrainerReserveService {

	private TrainerReserveDao trainerReserveDao;
	
	@Autowired
	public void setTrainerReserveDao(TrainerReserveDao trainerReserveDao) {
		this.trainerReserveDao = trainerReserveDao;
	}
	
	
	@Override
	public List<TrainerReserve> getList() {
		return trainerReserveDao.trainerselectAll();
	}
	
	@Override
	public List<TrainerReserve> getList2() {
		return trainerReserveDao.gymselectAll();
	}

	@Transactional
	@Override
	public void writeTrainerReserve(TrainerReserve trainerReserve) {
		trainerReserveDao.insertTrainerReserve(trainerReserve);
	}

	@Override
	public TrainerReserve getTrainerReserve(int id) {
		trainerReserveDao.updateViewCnt(id);
		return trainerReserveDao.selectOne(id);
	}

	@Transactional
	@Override
	public void modifyTrainerReserve(TrainerReserve trainerReserve) {
		trainerReserveDao.updateTrainerReserve(trainerReserve);
	}

	@Transactional
	@Override
	public void removeTrainerReserve(int id) {
		trainerReserveDao.deleteTrainerReserve(id);
	}



}
