package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;


@Service
public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao;
	
	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	
	@Override
	public List<Board> getList() {
		return boardDao.selectAll();
	}

	@Transactional
	@Override
	public void writeBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public Board getBoard(int id) {
		boardDao.updateViewCnt(id);
		return boardDao.selectOne(id);
	}

	@Transactional
	@Override
	public void modifyBoard(Board board) {
		boardDao.updateBoard(board);
	}

	@Transactional
	@Override
	public void removeBoard(int id) {
		boardDao.deleteBoard(id);
	}


	@Override
	public List<Board> search(SearchCondition condition) {
		return boardDao.search(condition);
	}


	@Override
	public void updateLikeCntUp(int boardId) {
		boardDao.updateLikeCntUp(boardId);
	}


	@Override
	public void updateLikeCntDown(int boardId) {
		boardDao.updateLikeCntDown(boardId);
	}

}
