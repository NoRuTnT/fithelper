package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.TrainerReserve;


public interface TrainerReserveDao {
	public List<TrainerReserve> trainerselectAll();
	
	public List<TrainerReserve> gymselectAll();

	public TrainerReserve selectOne(int id);

	public void insertTrainerReserve(TrainerReserve trainerReserve);

	public void deleteTrainerReserve(int id);

	public void updateTrainerReserve(TrainerReserve trainerReserve);

	public void updateViewCnt(int id);

	
}
