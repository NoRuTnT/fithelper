package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Comment;

public interface CommentDao {
	public List<Comment> selectAll();

	
	public void insertComment(Comment comment);

	public void deleteComment(int id);

	

}
