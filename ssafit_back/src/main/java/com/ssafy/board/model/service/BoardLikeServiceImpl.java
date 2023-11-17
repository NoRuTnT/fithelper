package com.ssafy.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.BoardLikeDao;
import com.ssafy.board.model.dto.BoardLike;

@Service
public class BoardLikeServiceImpl implements BoardLikeService{
	
	@Autowired
	private BoardLikeDao boardLikeDao;

	@Override
	public void addBoardLike(BoardLike boardLike) {
		boardLikeDao.insertBoardLike(boardLike);
	}

	@Override
	public void cancelBoardLike(int userId) {
		boardLikeDao.deleteBoardLike(userId);
	}

}
