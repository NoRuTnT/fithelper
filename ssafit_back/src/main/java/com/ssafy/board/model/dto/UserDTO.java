package com.ssafy.board.model.dto;

public class UserDTO {
	
	private String email; // 사용자 이메일
	private String levelId; // 사용자의 등급을 나타내는 숫자
	private String name; // 사용자의 이름
	private String nickname; // 사용자 닉네임
	private int cash; // 사용자가 가지고 있는 포인트
	private String role; //권한나누기
	private String token;
	// 필드 선언 (이미 존재하는 필드들)
    private String error; // 오류 메시지를 저장할 필드
	
	public UserDTO() {
	}
	

	public UserDTO(String email, String levelId, String name, String nickname, int cash, String role, String token) {
		super();
		this.email = email;
		this.levelId = levelId;
		this.name = name;
		this.nickname = nickname;
		this.cash = cash;
		this.role = role;
		this.token = token;
	}


	public void setError(String error) {
        this.error = error;
    }

    // 오류 메시지를 가져오는 메소드
    public String getError() {
        return error;
    }
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", levelId=" + levelId + ", name=" + name + ", nickname=" + nickname
				+ ", cash=" + cash + ", role=" + role + ", token=" + token + "]";
	}

	
}
