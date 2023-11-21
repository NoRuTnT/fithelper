package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Comment;

public interface CommentDao {
	/**현재 게시글 id에 해당하는 댓글들만 가져오면 된다.*/
	public List<Comment> selectAll(int boardId);

	public void insertComment(Comment comment);

	public void deleteComment(int id);

	

}
