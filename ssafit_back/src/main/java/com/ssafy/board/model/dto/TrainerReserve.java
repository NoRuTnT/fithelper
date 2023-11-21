package com.ssafy.board.model.dto;

public class TrainerReserve {
	int reserveId;
	int trainerId;
	int gymId;
	String reserveTime;
	String meetTime;
	
	public TrainerReserve() {
	}

	public TrainerReserve(int reserveId, int trainerId, int gymId, String reserveTime, String meetTime) {
		super();
		this.reserveId = reserveId;
		this.trainerId = trainerId;
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

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
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
		return "TrainerReserve [reserveId=" + reserveId + ", trainerId=" + trainerId + ", gymId=" + gymId
				+ ", reserveTime=" + reserveTime + ", meetTime=" + meetTime + "]";
	}

	
	
	

}
