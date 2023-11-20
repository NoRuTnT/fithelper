package com.ssafy.board.model.dto;

public class UserReserve {
	int reserveId;
	int userId;
	int gymId;
	String reserveTime;
	String meetTime;
	
	public UserReserve() {
	}

	public UserReserve(int reserveId, int userId, int gymId, String reserveTime, String meetTime) {
		super();
		this.reserveId = reserveId;
		this.userId = userId;
		this.gymId = gymId;
		this.reserveTime = reserveTime;
		this.meetTime = meetTime;
	}

	public int getReserveId() {
		return reserveId;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}

	@Override
	public String toString() {
		return "UserReserve [reserveId=" + reserveId + ", userId=" + userId + ", gymId=" + gymId + ", reserveTime="
				+ reserveTime + ", meetTime=" + meetTime + "]";
	}
	
	

}
