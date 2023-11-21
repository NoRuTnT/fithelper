package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Gymowner;

public interface GymownerService {
	List<Gymowner> getGymownerList();
	
	int signup(Gymowner gymowner);
	// JWT login 형태로 대체하기
	Gymowner login(Gymowner gymowner);
	
	void removeGymowner(int gymownerId);
	
	void modifyGymowner(Gymowner gymowner);
	
	boolean isEmailUnique(Integer id, String email);
}
