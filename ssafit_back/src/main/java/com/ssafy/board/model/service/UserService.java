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

	 UserDTO save(User user, MultipartFile multipartFile) throws IOException;
	

	ResponseEntity<UserDTO> login(Map<String, String> body) throws IllegalAccessException;

	boolean isEmailUnique(Integer id, String email);
	
}