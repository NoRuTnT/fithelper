package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.GymownerDao;
import com.ssafy.board.model.dto.Gymowner;

@Service
public class GymownerServiceImpl implements GymownerService {
	
	@Autowired
	private GymownerDao gymownerDao;

	@Override
	public List<Gymowner> getGymownerList() {
		return gymownerDao.selectAll();
	}

	@Override
	public int signup(Gymowner gymowner) {
		return gymownerDao.insertGymowner(gymowner);
	}
	// JWT 방식으로 수정하기
	@Override
	public Gymowner login(Gymowner gymowner) {
		Gymowner tmp = gymownerDao.selectOne(gymowner.getGymownerId());
		if(tmp != null && tmp.getPassword().equals(gymowner.getPassword()))
			return tmp;
		return null;
	}

	@Override
	public void removeGymowner(int gymownerId) {
		gymownerDao.deleteGymowner(gymownerId);
	}

	@Override
	public void modifyGymowner(Gymowner gymowner) {
		gymownerDao.updateGymowner(gymowner);
	}

	@Override
	public boolean isEmailUnique(Integer id, String email) {
		Gymowner gymowner = gymownerDao.findByEmail(email); // 데이터베이스에서 이메일로 사용자 조회
		
		if(gymowner == null) {
			// 사용자가 없으면 이메일은 고유합니다.
			return true;
		}else if(email != null && gymowner.getEmail().equals(email)) {
			// 주어진 ID가 현재 사용자의 ID와 동일하면, 이메일은 고유합니다.
	        // 이 경우, 사용자가 자신의 이메일을 유지하려는 상황을 처리합니다.
	        return true;
		}
		// 그 외의 경우, 이메일은 이미 사용 중입니다.
	    return false;
	}
	
	
}
