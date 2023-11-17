package com.ssafy.board.model.dto;

public class Trainer {
	private int trainerId; // 트레이너 회원 고유식별자
	// 트레이너 등급을 2단계로 표시(1:우수트레이너/0:일반트레이너)
	private int trainerLevel;
	private String id; // 트레이너 아이디
	private String name; // 트레이너 이름
	private String password; // 트레이너 비밀번호
	private String nickname; // 트레이너 닉네임
	private String birth; // 트레이너 생일
	private String email; // 트레이너 이메일
	private String phonenum; // 트레이너 핸드폰 번호
	private String address; // 트레이너 주소
	private int cash; // 트레이너가 가지고 있는 포인트
	private String sex; // 트레이너 성별 (1:남성 /2:여성)
	public Trainer() {
	}
	
	public Trainer(int trainerId, int trainerLevel, String id, String name, String password, String nickname,
			String birth, String email, String phonenum, String address, int cash, String sex) {
		super();
		this.trainerId = trainerId;
		this.trainerLevel = trainerLevel;
		this.id = id;
		this.name = name;
		this.password = password;
		this.nickname = nickname;
		this.birth = birth;
		this.email = email;
		this.phonenum = phonenum;
		this.address = address;
		this.cash = cash;
		this.sex = sex;
	}

	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	public int getTrainerLevel() {
		return trainerLevel;
	}
	public void setTrainerLevel(int trainerLevel) {
		this.trainerLevel = trainerLevel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Trainer [trainerId=" + trainerId + ", trainerLevel=" + trainerLevel + ", id=" + id + ", name=" + name
				+ ", password=" + password + ", nickname=" + nickname + ", birth=" + birth + ", email=" + email
				+ ", phonenum=" + phonenum + ", address=" + address + ", cash=" + cash + ", sex=" + sex + "]";
	}
	
}
