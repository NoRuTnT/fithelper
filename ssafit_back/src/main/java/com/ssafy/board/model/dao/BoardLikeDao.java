package com.ssafy.board.model.dao;

import com.ssafy.board.model.dto.BoardLike;

public interface BoardLikeDao {
	/**좋아요 표시하기*/ 
	public void insertBoardLike(BoardLike boardLike);
	/**좋아요 취소하기. 사용자 번호를 입력으로 받는다.*/
	public void deleteBoardLike(int userId);
}
