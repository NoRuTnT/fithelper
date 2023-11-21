package com.ssafy.board.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.dao.UserDao;
import com.ssafy.board.model.dto.User;
import com.ssafy.board.model.dto.UserDTO;
import com.ssafy.board.security.JwtTokenUtil;
import com.ssafy.board.security.JwtUserDetails;
import com.ssafy.board.security.JwtUserDetailsService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public ResponseEntity<UserDTO> login(@RequestBody Map<String, String> body) {
	    String email = body.get("email");
	    String password = body.get("password");

	    User user = userDao.findByEmail(email); // 데이터베이스에서 사용자 조회

	    if(user == null || !passwordEncoder.matches(password, user.getPassword())){
	        // 예외 대신 적절한 HTTP 응답을 반환
	        UserDTO errorUserDTO = new UserDTO();
	        errorUserDTO.setError("Invalid email or password");
	        return new ResponseEntity<>(errorUserDTO, HttpStatus.UNAUTHORIZED);
	    }
	    System.out.println("UserDao: " + userDao);
	    System.out.println("PasswordEncoder: " + passwordEncoder);
	    System.out.println("JwtTokenUtil: " + jwtTokenUtil);
	    System.out.println("User: " + user);

	    // userDetailsService를 사용하여 UserDetails 객체를 얻습니다.
	    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	   
	    JwtUserDetails jwtUserDetails = (JwtUserDetails) userDetails; // 캐스팅
	    
	    System.out.println("JwtUserDetails - Email: " + jwtUserDetails.getUsername());
	    System.out.println("JwtUserDetails - Nickname: " + jwtUserDetails.getNickname());
	    
	    
	    UserDTO userDTO = new UserDTO();
	    
//	    String token = jwtTokenUtil.generateToken(jwtUserDetails.getUsername(),jwtUserDetails.getNickname());
	    String token = jwtTokenUtil.generateToken(jwtUserDetails);
	    userDTO.setToken(token); // 토큰 설정

	    return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@Override
	public int signup(User user) {
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword); // 해시된 비밀번호로 설정
	    return userDao.insertUser(user);
	}


//	@Override
//	public User login(User user) {
//		User tmp = userDao.selectOne(user.getId());
//		if(tmp != null && tmp.getPassword().equals(user.getPassword()))
//			return tmp;
//		return null;
//	}

	@Transactional
	@Override
	public void removeUser(int userId) {
		userDao.deleteUser(userId);
		
	}

	@Transactional
	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
	}


	@Override
	public List<User> getFollowingList(int userId) {
		return userDao.selectAllFollowing(userId);
	}


	@Override
	public List<User> getFollowerList(int userId) {
		return userDao.selectAllFollower(userId);
	}
	
	@Override
	public UserDTO save(User user, MultipartFile multipartFile) throws IOException {		  
	    if(user.getEmail() != null) {
	        User existingUser = userDao.findByEmail(user.getEmail());
	        if(existingUser != null) {
	            existingUser.setName(user.getName());
	            if(user.getPassword() != null && !user.getPassword().isEmpty()) {
	                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
	            }	            
	            userDao.updateUser(existingUser);
	            return new UserDTO();
	        }
	    } else {
	        user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 해시 처리
	        userDao.insertUser(user); // 새 사용자 추가
	        return new UserDTO();
	    }
	    return null;
	}


	


	@Override
	public boolean isEmailUnique(Integer id, String email) {
	    User user = userDao.findByEmail(email); // 데이터베이스에서 이메일로 사용자 조회

	    if (user == null) {
	        // 사용자가 없으면 이메일은 고유합니다.
	        return true;
	    } else if (email != null && user.getEmail().equals(email)) {
	        // 주어진 ID가 현재 사용자의 ID와 동일하면, 이메일은 고유합니다.
	        // 이 경우, 사용자가 자신의 이메일을 유지하려는 상황을 처리합니다.
	        return true;
	    }
	    // 그 외의 경우, 이메일은 이미 사용 중입니다.
	    return false;
	}

	


	

}
