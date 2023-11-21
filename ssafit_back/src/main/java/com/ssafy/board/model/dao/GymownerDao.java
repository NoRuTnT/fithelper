package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Gymowner;

public interface GymownerDao {
	List<Gymowner> selectAll();
	
	int insertGymowner(Gymowner gymowner);
	
	Gymowner selectOne(int gymownerId);
	
	public void deleteGymowner(int gymownerId);
	
	public void updateGymowner(Gymowner gymowner);
	
	Gymowner findByEmail(String email);
}
