package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;

public interface BoardService {
	List<Board> getList();

	void writeBoard(Board board);

	Board getBoard(int id);

	void modifyBoard(Board board);

	void removeBoard(int id);

	List<Board> search(SearchCondition condition);
	
	void updateLikeCntUp(int boardId);
	
	void updateLikeCntDown(int boardId);
}
