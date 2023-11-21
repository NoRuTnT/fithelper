package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.CommentDao;
import com.ssafy.board.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao;
	
	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	
	@Override
	public List<Comment> getList(int boardId) {
		return commentDao.selectAll(boardId);
	}

	@Transactional
	@Override
	public void writeComment(Comment comment) {
		commentDao.insertComment(comment);
	}



	@Transactional
	@Override
	public void removeComment(int id) {
		commentDao.deleteComment(id);
	}



}
