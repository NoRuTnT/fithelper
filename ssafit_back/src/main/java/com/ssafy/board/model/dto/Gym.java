package com.ssafy.board.model.dto;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "장소 정보")
public class Gym {
	private int gymId;
	private int userId;

	private String name;
	private String category;
	private String status;
	private int price;
	private String description;
	private String reserveTime;
	private Timestamp meetTime;
	
	public Gym() {
	}

	public Gym(int gymId, int userId, String name, String category, String status, int price,
			String description, String reserveTime, Timestamp meetTime) {
		super();
		this.gymId = gymId;
		this.userId = userId;
		this.name = name;
		this.category = category;
		this.status = status;
		this.price = price;
		this.description = description;
		this.reserveTime = reserveTime;
		this.meetTime = meetTime;
	}

	public int getGymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	public Timestamp getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(Timestamp meetTime) {
		this.meetTime = meetTime;
	}

	@Override
	public String toString() {
		return "Gym [gymId=" + gymId + ", userId=" + userId + ", name=" + name + ", category=" + category + ", status="
				+ status + ", price=" + price + ", description=" + description + ", reserveTime=" + reserveTime
				+ ", meetTime=" + meetTime + "]";
	}

	

	
	
	
	

	
	
}
