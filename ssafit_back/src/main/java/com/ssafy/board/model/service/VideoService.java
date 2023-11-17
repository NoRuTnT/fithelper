package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Video;

public interface VideoService {
	/**조회수 순으로 전체 영상 조회*/
	List<Video> getVideoByViewCnt();
	/**운동 부위에 따른 영상 리스트 조회*/
	List<Video> getVideo(String fitPartName);
	/**운동 영상 등록*/
	public void writeVideo(Video video);
	/**운동 영상 수정*/
	public void modifyVideo(Video video);
	/**운동 영상 삭제*/
	public void removeVideo(int videoId);
	/**클릭한 영상 조회수 증가*/
	public void countUpVideo(int videoId);
}
