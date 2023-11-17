package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.VideoDao;
import com.ssafy.board.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;
	@Override
	public List<Video> getVideoByViewCnt() {
		return videoDao.selectAllByViewCnt();
	}

	@Override
	public List<Video> getVideo(String fitPartName) {
		return videoDao.searchByPart(fitPartName);
	}

	@Override
	public void writeVideo(Video video) {
		videoDao.insertVideo(video);
		
	}

	@Override
	public void modifyVideo(Video video) {
		videoDao.updateVideo(video);
	}

	@Override
	public void removeVideo(int videoId) {
		videoDao.deleteVideo(videoId);
	}

	@Override
	public void countUpVideo(int videoId) {
		videoDao.updateVideoViewCount(videoId);
		
	}
	
}
