package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Trainer;

public interface TrainerDao {
	List<Trainer> selectAll();

	int insertTrainer(Trainer trainer);
	
	Trainer selectOne(int trainerId);
	
	public void deleteTrainer(int trainerId);

	public void updateTrainer(Trainer trainer);
  
	Trainer findByEmail(String email);
}
