package com.example.boot14.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot14.dto.CafeDto;
import com.example.boot14.service.CafeService;

@RestController
public class CafeController {
	
	@Autowired private CafeService service;
	
	@PutMapping("/cafes/{num}")
	public Map<String, Object> update(@PathVariable("num") int num, @RequestBody  CafeDto dto){
		//dto 에 글번호를 담고 
		dto.setNum(num);
		//수정 반영하기 
		service.updateContent(dto);
		
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/cafes/{num}/edit")
	public CafeDto edit(@PathVariable("num") int num) {
		
		return service.getData(num);
	}
	
	@DeleteMapping("/cafes/{num}")
	public Map<String, Object> delete(@PathVariable("num") int num){
		//서비스 객체를 이용해서 num 에 해당하는 글을 삭제한다. 
		service.deleteContent(num);
		
		return Map.of("isSuccess", true);
	}
	
	
	@GetMapping("/cafes/{num}")
	public Map<String, Object> detail(@PathVariable("num") int num, CafeDto dto){
		//dto 에 글번호도 같이 담아준다( dto 에는 검색조건과 검색 키워드에 관련된 정보가 들어 있다)
		dto.setNum(num);
		
		return service.getDetail(dto);
	}
	
	@GetMapping("/cafes")
	public Map<String, Object> list(CafeDto dto){
		//CafeDto 객체에는 GET 방식 파라미터로 전달되는 pageNum, condition, keyword 가 들어 있다.
		
		return service.getList(dto);
	}
	
	
	/*
	 *  클라이언트가 json 문자열을 요청 파라미터로 전달 하기 때문에 
	 *  파라미터를 추출할때는 @RequestBody 어노테이션이 필요하다 
	 */
	@PostMapping("/cafes")
	public Map<String, Object> insert(@RequestBody CafeDto dto){
		
		service.saveContent(dto);
		
		return Map.of("isSuccess", true);
	}
 
}






