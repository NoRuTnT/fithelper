package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Comment;

public interface CommentService {
	List<Comment> getList(int boardId);

	void writeComment(Comment comment);


	void removeComment(int id);

}
