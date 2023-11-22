package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.service.ClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="Class 컨트롤러")
@CrossOrigin("*")
public class ClassRestController {
	
	@Autowired
	private ClassService classService;
	
	/**개설된 전체 방 list를 보여줌*/
	@GetMapping("/class")
	@ApiOperation(value="개설된 전체 방 list를 보여줌")
	public ResponseEntity<?> list(){
		List<Class> list = classService.getClassList();
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Class>>(list , HttpStatus.OK);
	}
	/**하나의 방 정보를 보여줌*/
	@GetMapping("/class/{classId}")
	@ApiOperation(value="하나의 방 정보를 보여줌")
	public ResponseEntity<?> detail(@PathVariable int classId){
		Class c = classService.detail(classId);
		if(c == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Class>(c , HttpStatus.OK);
	}
	
	/**트레이너가 방을 생성*/
	@PostMapping("/class")
	@ApiOperation(value="트레이너가 방을 생성")
	public ResponseEntity<Void> create(@RequestBody Class c){
		classService.createClass(c);
		return new ResponseEntity<Void>(HttpStatus.CREATED); 
	}
	/**트레이너가 방의 정보를 수정*/
	@PutMapping("/class")
	@ApiOperation(value="트레이너가 방의 정보를 수정")
	public ResponseEntity<Void> update(@RequestBody Class c){
		classService.modifyClass(c);
		return new ResponseEntity<Void>(HttpStatus.OK); 
	}
	/**트레이너가 방을 삭제. participate테이블에 대한 delete도 동시에 발생한다.*/
	@DeleteMapping("/class/{classId}")
	@ApiOperation(value="트레이너가 방을 삭제")
	public ResponseEntity<Void> delete(@PathVariable int classId){
		classService.removeClass(classId);
		return new ResponseEntity<Void>(HttpStatus.OK); 
	}
	/**트레이너가 자신이 생성한 방 리스트를 확인*/
	@GetMapping("/class/trainerSelect/{trainerId}")
	@ApiOperation(value="트레이너의 방 list 확인")
	public ResponseEntity<?> selectTrainerClass(@PathVariable int trainerId){
		List<Class> list = classService.selectTrainerClassList(trainerId);
		if(list==null || list.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Class>>(list, HttpStatus.OK); 
	}
	/**user가 자신이 가입한 방 리스트를 확인*/
	@GetMapping("/class/userSelect/{userId}")
	@ApiOperation(value="user의 방 list 확인")
	public ResponseEntity<?> selectUserClass(@PathVariable int userId){
		List<Class> list = classService.selectUserClassList(userId);
		if(list==null || list.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Class>>(list, HttpStatus.OK); 
	}
}
