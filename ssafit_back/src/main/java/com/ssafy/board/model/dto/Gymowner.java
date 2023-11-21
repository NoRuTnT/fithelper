package com.ssafy.board.model.dto;

public class Gymowner {
	private int gymownerId;
	private String name;
	private String email;
	private String password;
	private String nickname;
	private String phonenum;
	private int cash;
	
	public Gymowner() {
	}

	public int getGymownerId() {
		return gymownerId;
	}

	public void setGymownerId(int gymownerId) {
		this.gymownerId = gymownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "Gymowner [gymownerId=" + gymownerId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", nickname=" + nickname + ", phonenum=" + phonenum + ", cash=" + cash + "]";
	}
	
}
