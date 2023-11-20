package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Gym;
import com.ssafy.board.model.dto.SearchCondition;

public interface GymService {
	List<Gym> getList();

	void writeGym(Gym gym);

	Gym getGym(int id);

	void modifyGym(Gym gym);

	void removeGym(int id);

	List<Gym> search(SearchCondition condition);
	
	void updateLikeCntUp(int gymId);
	
	void updateLikeCntDown(int gymId);
}
