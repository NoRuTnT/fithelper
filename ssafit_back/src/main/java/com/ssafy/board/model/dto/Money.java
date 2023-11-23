package com.ssafy.board.model.dto;

public class Money {
	private String email;
	private String money;
	
	public Money() {
	}

	public Money(String email, String money) {
		super();
		this.email = email;
		this.money = money;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Money [email=" + email + ", money=" + money + "]";
	}
	 
	
	
	
}
