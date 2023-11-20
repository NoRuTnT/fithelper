package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Gym;
import com.ssafy.board.model.dto.SearchCondition;


public interface GymDao {
	public List<Gym> selectAll();

	public Gym selectOne(int id);

	public void insertGym(Gym gym);

	public void deleteGym(int id);

	public void updateGym(Gym gym);

	public void updateViewCnt(int id);

	public List<Gym> search(SearchCondition condition);
	/** 좋아요 개수를 update함*/
	public void updateLikeCntUp(int gymId);
	
	public void updateLikeCntDown(int gymId);
	
}
