package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Gym;
import com.ssafy.board.model.dto.SearchCondition;


public interface GymDao {
	
	public void insertGym(Gym gym);
	
	public List<Gym> selectGymList(int userId);
	
	public Gym selectOne(int gymId);
	
	public void updateGym(Gym gym);
	
	public void deleteGym(int gymId);
	
}
