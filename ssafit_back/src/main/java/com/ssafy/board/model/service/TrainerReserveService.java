package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.TrainerReserve;


public interface TrainerReserveService {
	List<TrainerReserve> getList();
	
	List<TrainerReserve> getList2();

	void writeTrainerReserve(TrainerReserve trainerReserve);

	TrainerReserve getTrainerReserve(int id);

	void modifyTrainerReserve(TrainerReserve trainerReserve);

	void removeTrainerReserve(int id);

}

