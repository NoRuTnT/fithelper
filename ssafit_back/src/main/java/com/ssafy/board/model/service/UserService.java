package com.ssafy.board.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.dto.User;
import com.ssafy.board.model.dto.UserDTO;

public interface UserService {

	List<User> getUserList();

	int signup(User user);

//	User login(User user);
	
	void removeUser(int userId);
	
	void modifyUser(User user);

	// user와 연결해서 구현해야 되는 부분
	/**선택한 사람이 팔로우 하는 모든 사람을 보여줌*/
	List<User> getFollowingList(int userId);
	/**선택한 사람을 팔로우 하는 모든 사람을 보여줌*/
	List<User> getFollowerList(int userId);


	 UserDTO save(User user, MultipartFile multipartFile) throws IOException;
	

	ResponseEntity<UserDTO> login(Map<String, String> body) throws IllegalAccessException;

	boolean isEmailUnique(Integer id, String email);
	
}