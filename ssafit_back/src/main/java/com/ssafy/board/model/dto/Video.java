package com.ssafy.board.model.dto;

public class Video {
	private int videoId;
	private String title;
	private String fitPartName;
	private String channelName;
	private int viewCnt;
	private String url;
	public Video() {
	}
	public Video(String title, String fitPartName, String channelName, String url) {
		super();
		this.title = title;
		this.fitPartName = fitPartName;
		this.channelName = channelName;
		this.url = url;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFitPartName() {
		return fitPartName;
	}
	public void setFitPartName(String fitPartName) {
		this.fitPartName = fitPartName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", title=" + title + ", fitPartName=" + fitPartName + ", channelName="
				+ channelName + ", viewCnt=" + viewCnt + ", url=" + url + "]";
	}
	
	
}
