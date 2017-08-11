package org.kyu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {
	
	@PostMapping("")
	public ResponseEntity<String> create() {
		ResponseEntity<String> entity = null;
		return entity;
	}
	
	@GetMapping("")
	public ResponseEntity<String> read() {
		ResponseEntity<String> entity = null;
		return entity;
	}
	
	@PutMapping("")
	public ResponseEntity<String> update() {
		ResponseEntity<String> entity = null;
		return entity;
	}
	
	@DeleteMapping("")
	public ResponseEntity<String> delete() {
		ResponseEntity<String> entity = null;
		return entity;
	}
}
