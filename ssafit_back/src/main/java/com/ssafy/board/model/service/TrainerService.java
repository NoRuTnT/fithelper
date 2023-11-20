package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Trainer;

public interface TrainerService {
	List<Trainer> getTrainerList();
	
	int signup(Trainer trainer);
	// JWT login형태로 대체하기
	Trainer login(Trainer trainer);
	
	void removeTrainer(int trainerId);
	
	void modifyTrainer(Trainer trainer);
	
	boolean isEmailUnique(Integer id, String email);
}
