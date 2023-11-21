package com.ssafy.board.model.dao;

import java.util.List;

public interface ClassDao {
	
	/**개설된 전체 방 list를 보여줌*/
	public List<Class> selectAll();
	/**하나의 방 정보를 보여줌*/
	public Class selectOne(int classId);
	/**트레이너가 방을 생성*/
	public void insert(Class c);
	/**트레이너가 방의 정보를 수정*/
	public void update(Class c);
	/**트레이너가 방을 삭제. participate테이블에 대한 delete도 동시에 발생한다.*/
	public void delete(int classId);
}
