package com.ssafy.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.Trainer;
import com.ssafy.board.model.service.TrainerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api-trainer")
@Api(tags="트레이너 컨트롤러")
public class TrainerRestController {
	@Autowired
	private TrainerService trainerService;
	
	@GetMapping("trainers")
	public List<Trainer> trainerList(Model model){
		return trainerService.getTrainerList();
	}
	
	@PostMapping("signup")
	public ResponseEntity<Trainer> signup(@RequestBody Trainer trainer) {
		System.out.println(trainer);
	    trainerService.signup(trainer);
	    trainer.setPassword(null); // 민감한 정보 숨기기
	    return new ResponseEntity<>(trainer, HttpStatus.CREATED);
	}
	
	@PostMapping("/trainer/check_email")
    public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email){
        return trainerService.isEmailUnique(id, email)? "OK" : "Duplicated";
    }
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable int trainerId){
		trainerService.removeTrainer(trainerId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("update") 
	public ResponseEntity<Void> update(@RequestBody Trainer trainer){
		trainerService.modifyTrainer(trainer);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Trainer trainer, HttpSession session) {
		Trainer tmp = trainerService.login(trainer);
		if(tmp == null)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);  //로그인 페이지로 다시 튕기게 만듬
		
		session.setAttribute("loginTrainer", tmp.getNickname());
		return new ResponseEntity<String>(tmp.getNickname(),HttpStatus.OK);
	}
	
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
