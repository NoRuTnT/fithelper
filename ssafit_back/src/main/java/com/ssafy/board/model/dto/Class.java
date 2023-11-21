package com.ssafy.board.model.dto;

import java.util.Date;

public class Class {
	// 아래는 Class 테이블의 항목
	private int classId; // 운동 수업  식별번호
	private int trainerId; // 트레이너의 식별번호
	// 아래는 class_info 테이블의 항목
	private int name; // class_info의 사용하지 않는 고유값
	private String detail; // 수업 세부설명
	private String cost; // 수업비용
	private Date time; // 강의시간
	
	private String category; // 운동부위 항목
	
	public Class() {
	}

	public Class(int classId, int trainerId, int name, String detail, String cost, Date time, String category) {
		super();
		this.classId = classId;
		this.trainerId = trainerId;
		this.name = name;
		this.detail = detail;
		this.cost = cost;
		this.time = time;
		this.category = category;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", trainerId=" + trainerId + ", name=" + name + ", detail=" + detail
				+ ", cost=" + cost + ", time=" + time + ", category=" + category + "]";
	}

	
}
