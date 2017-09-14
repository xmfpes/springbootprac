package org.kyu.web;

import java.util.List;

import org.kyu.domain.Calendar;
import org.kyu.domain.Male;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class JsonTestController {
	
	@PostMapping("/test")
	public ResponseEntity<String> load(@RequestBody Male jsonMale) {
		ResponseEntity<String> entity = null;
		try {
			Male male = new Male();
			male.updateMale(jsonMale);
			System.out.println(male);
			entity = new ResponseEntity<String>("SUCCEUSS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
