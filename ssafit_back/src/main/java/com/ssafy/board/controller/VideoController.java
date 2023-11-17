package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.SearchCondition;
import com.ssafy.board.model.dto.Video;
import com.ssafy.board.model.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api-video")
@Api(tags="운동영상 컨트롤러")
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	/**1. 조회수 순으로 전체 영상 조회*/
	@GetMapping("/video")
	@ApiOperation(value = "조회수 순 영상 조회", notes = "조회수 많은 기준으로 내림차순 정렬")
	public ResponseEntity<?> list(){
		List<Video> list = videoService.getVideoByViewCnt();
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	
	
	/**2. 운동 부위에 따른 영상 리스트 조회*/
	@GetMapping("/video/{fitPartName}")
	public ResponseEntity<?> partList(@PathVariable String fitPartName){
//		Board board = boardService.getBoard(id);
		List<Video> list = videoService.getVideo(fitPartName);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	/**3. 운동 영상 등록*/
	@PostMapping("/video")
	public ResponseEntity<Video> write(@RequestBody Video video){
		videoService.writeVideo(video);
		return new ResponseEntity<Video>(video, HttpStatus.CREATED);
	}
	/**4. 운동 영상 수정*/
	@PutMapping("/video")
	public ResponseEntity<Void> update(@RequestBody Video video){
		videoService.modifyVideo(video);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/** 5. 운동 영상 삭제 */
	@DeleteMapping("/video/{videoId}")
	public ResponseEntity<Void> delete(@PathVariable int videoId){
		videoService.removeVideo(videoId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	/** 6. 조회한 영상 조회수 증가*/
	@PutMapping("/video/{videoId}")
	public ResponseEntity<Void> updateVideoViewCnt(@PathVariable int videoId){
		videoService.countUpVideo(videoId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
		
		
		
		
		
}
