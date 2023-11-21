package com.ssafy.board.model.dto;
/**운동수업 참가자의 DTO*/
/** 여기서는 가입과 삭제에 대한 정보만 관리함 */
public class Participate {
	private int participateId; // 참가자의 고유식별번호
	private int userId; // 일반회원 식별변호. 일반번호와 고유키로 연결됨
	private int classId; // 수강하는 수업의 식별번호
	
	public Participate() {
	}
	
	public Participate(int participateId, int userId, int classId) {
		super();
		this.participateId = participateId;
		this.userId = userId;
		this.classId = classId;
	}

	public int getParticipateId() {
		return participateId;
	}

	public void setParticipateId(int participateId) {
		this.participateId = participateId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "Participate [participateId=" + participateId + ", userId=" + userId + ", classId=" + classId + "]";
	}
	
}
