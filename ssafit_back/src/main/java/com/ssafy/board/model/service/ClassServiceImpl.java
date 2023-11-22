package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.ClassDao;

@Service
public class ClassServiceImpl implements ClassService{

	@Autowired
	private ClassDao classDao;

	@Override
	public List<Class> getClassList() {
		return classDao.selectAll();
	}

	@Override
	public Class detail(int classId) {
		return classDao.selectOne(classId);
	}
	
	@Override
	public void createClass(Class c) {
		classDao.insert(c);
	}

	@Override
	public void modifyClass(Class c) {
		classDao.update(c);
	}

	@Override
	public void removeClass(int classId) {
		classDao.delete(classId);
	}

	@Override
	public List<Class> selectTrainerClassList(int trainerId) {
		return classDao.selectTrainerClassList(trainerId);
	}

	@Override
	public List<Class> selectUserClassList(int userId) {
		return classDao.selectUserClassList(userId);
	}

	
	
}
