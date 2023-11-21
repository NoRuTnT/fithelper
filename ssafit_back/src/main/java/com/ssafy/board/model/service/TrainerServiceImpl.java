package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.TrainerDao;
import com.ssafy.board.model.dto.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService{

	@Autowired
	private TrainerDao trainerDao;
	
	@Override
	public List<Trainer> getTrainerList() {
		return trainerDao.selectAll();
	}

	@Override
	public int signup(Trainer trainer) {
		return trainerDao.insertTrainer(trainer);
	}
	// JWT 방식으로 수정하기
	@Override
	public Trainer login(Trainer trainer) {
		Trainer tmp = trainerDao.selectOne(trainer.getTrainerId());
		if(tmp != null && tmp.getPassword().equals(trainer.getPassword()))
			return tmp;
		return null;
	}

	@Override
	public void removeTrainer(int trainerId) {
		trainerDao.deleteTrainer(trainerId);
	}

	@Override
	public void modifyTrainer(Trainer trainer) {
		trainerDao.updateTrainer(trainer);
	}

	@Override
	public boolean isEmailUnique(Integer id, String email) {
		Trainer trainer = trainerDao.findByEmail(email); // 데이터베이스에서 이메일로 사용자 조회
		
		if(trainer == null) {
			// 사용자가 없으면 이메일은 고유합니다.
			return true;
		}else if(email != null && trainer.getEmail().equals(email)) {
			// 주어진 ID가 현재 사용자의 ID와 동일하면, 이메일은 고유합니다.
	        // 이 경우, 사용자가 자신의 이메일을 유지하려는 상황을 처리합니다.
	        return true;
		}
		// 그 외의 경우, 이메일은 이미 사용 중입니다.
	    return false;
	}

}
