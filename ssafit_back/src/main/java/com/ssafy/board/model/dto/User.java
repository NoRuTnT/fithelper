package com.ssafy.board.model.dto;

public class User {
	// user 테이블에 존재하는 속성
	private int userId; // 사용자의 식별번호
	private String name; // 사용자의 이름
	private String password; // 사용자의 비밀번호
	private String nickname; // 사용자 닉네임
	private String birth; // 사용자 성별
	private String email; // 사용자 이메일
	private String phonenum; // 사용자 핸드폰 번호
	private String address;	 // 사용자의 주소 
	private int cash; // 사용자가 가지고 있는 포인트
	private int totalcash; // 사용자가 지금까지충전한 전체 포인트
	private String sex;	// 회원 성별 (1:남성 /2:여성)
	// level 테이블에만 존재하는 속성
	private String levelName; // 회원의 등급에 대한 설명
	private String role; //권한나누기
	public User() {
	}
	

	public User(int userId, String name, String password, String nickname, String birth, String email, String phonenum,
			String address, int cash, int totalcash, String sex, String levelName, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.nickname = nickname;
		this.birth = birth;
		this.email = email;
		this.phonenum = phonenum;
		this.address = address;
		this.cash = cash;
		this.totalcash = totalcash;
		this.sex = sex;
		this.levelName = levelName;
		this.role = role;
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


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhonenum() {
		return phonenum;
	}


	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getCash() {
		return cash;
	}


	public void setCash(int cash) {
		this.cash = cash;
	}


	public int getTotalcash() {
		return totalcash;
	}


	public void setTotalcash(int totalcash) {
		this.totalcash = totalcash;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getLevelName() {
		return levelName;
	}


	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", nickname=" + nickname
				+ ", birth=" + birth + ", email=" + email + ", phonenum=" + phonenum + ", address=" + address
				+ ", cash=" + cash + ", totalcash=" + totalcash + ", sex=" + sex + ", levelName=" + levelName
				+ ", role=" + role + "]";
	}



	
	
}
