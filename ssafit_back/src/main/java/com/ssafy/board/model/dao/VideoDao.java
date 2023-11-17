package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Video;

public interface VideoDao {
	/** 운동 부위에 따른 영상 조회*/
	List<Video> searchByPart(String fitPartName);
	/** 조회수 순서로 전체 영상 조회*/
	List<Video> selectAllByViewCnt();
	/** 운동 영상 등록*/
	public void insertVideo(Video video);
	/** 운동 영상 수정*/
	public void updateVideo(Video video);
	/** 운동 영상 삭제*/
	public void deleteVideo(int videoId);
	/** 운동영상의 조회수 증가*/
	public void updateVideoViewCount(int videoId);
}
