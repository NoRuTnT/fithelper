package com.ssafy.board.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "장소 정보")
public class Gym {
	private int gymId;
	private String name;
	private String category;
	private String status;
	private int price;
	private String description;
	private int regDate; 
	
	public Gym() {
	}	

	public Gym(int gymId, String name, String category, String status, int price, String description,
			int regDate) {
		super();
		this.gymId = gymId;
		this.name = name;
		this.category = category;
		this.status = status;
		this.price = price;
		this.description = description;
		this.regDate = regDate;
	}	

	public int getGymId() {
		return gymId;
	}



	public void setGymId(int gymId) {
		this.gymId = gymId;
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



	public int getRegDate() {
		return regDate;
	}



	public void setRegDate(int regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Gym [gymId=" + gymId + ", name=" + name + ", category=" + category + ", status=" + status + ", price="
				+ price + ", description=" + description + ", regDate=" + regDate + "]";
	}

	



	
	
}
