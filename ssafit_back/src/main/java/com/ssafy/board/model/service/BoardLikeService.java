package com.ssafy.board.model.service;

import com.ssafy.board.model.dto.BoardLike;

public interface BoardLikeService {
	
	/**좋아요 표시하기*/ 
	void addBoardLike(BoardLike boardLike);
	/**좋아요 취소하기. 사용자 번호를 입력으로 받는다.*/
	void cancelBoardLike(int userId);
}
