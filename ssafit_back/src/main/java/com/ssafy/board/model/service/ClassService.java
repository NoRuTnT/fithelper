package com.ssafy.board.model.service;

import java.util.List;

public interface ClassService {
	
	/**개설된 전체 방 list를 보여줌*/
	List<Class> getClassList();
	/**하나의 방 정보를 보여줌*/
	Class detail(int classId);
	/**트레이너가 방을 생성*/
	void createClass(Class c);
	/**트레이너가 방의 정보를 수정*/
	void modifyClass(Class c);
	/**트레이너가 방을 삭제. participate테이블에 대한 delete도 동시에 발생한다.*/
	void removeClass(int classId);

}
