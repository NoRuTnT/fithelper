package com.ssafy.board.model.dto;

public class User {
	// user 테이블에 존재하는 속성
	private String userId; // 사용자의 식별번호
	private String levelId; // 사용자의 등급을 나타내는 숫자
	private String name; // 사용자의 이름
	private String password; // 사용자의 비밀번호
	private String nickname; // 사용자 닉네임
	private String birth; // 사용자 성별
	private String email; // 사용자 이메일
	private String phonenum; // 사용자 핸드폰 번호
	private String address;	 // 사용자의 주소 
	private int cash; // 사용자가 가지고 있는 포인트
	private String sex;	// 회원 성별 (1:남성 /2:여성)
	// level 테이블에만 존재하는 속성
	private String levelName; // 회원의 등급에 대한 설명

	private Role role; //권한나누기
	public User() {
	}
	
	public User(String userId, String levelId, String name, String password, String nickname, String birth,
			String email, String phonenum, String address, int cash, String sex, String levelName, Role role) {
		super();
		this.userId = userId;
		this.levelId = levelId;
		this.name = name;
		this.password = password;
		this.nickname = nickname;
		this.birth = birth;
		this.email = email;
		this.phonenum = phonenum;
		this.address = address;
		this.cash = cash;
		this.sex = sex;
		this.levelName = levelName;
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
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


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", levelId=" + levelId + ", name=" + name + ", password="
				+ password + ", nickname=" + nickname + ", birth=" + birth + ", email=" + email + ", phonenum="
				+ phonenum + ", address=" + address + ", cash=" + cash + ", sex=" + sex + ", levelName=" + levelName
				+ ", role=" + role + "]";
	}

	
	
}
