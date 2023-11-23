package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Gym;
import com.ssafy.board.model.dto.SearchCondition;

public interface GymService {

	void writeGym(Gym gym);
	
	List<Gym> getList(int userId);

	Gym getGym(int gymId);

	void modifyGym(Gym gym);

	void removeGym(int gymId);
}
