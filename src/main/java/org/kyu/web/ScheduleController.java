package org.kyu.web;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.kyu.domain.Calendar;
import org.kyu.domain.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private CalendarRepository calendarRepository;
	
	@GetMapping("/hihi")
	public String dkdk() {
		return "schedule/basic-views";
	}
	@GetMapping("/getcurrent")
	public @ResponseBody Long getindex() {
		return calendarRepository.count();
	}
	@GetMapping("")
	public String show() {
		return "/schedule/schedule";
	}
	
	@PostMapping("/load")
	public @ResponseBody List<Calendar> load() {
		List<Calendar> datalist = calendarRepository.findAll();
		return datalist;
	}
	@PostMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Calendar schedule){
		System.out.println("일정 업데이트 한다");
		System.out.println(schedule);
		ResponseEntity<String> entity = null;
		try {
			System.out.println("일정 업데이트");
			Calendar calendar = calendarRepository.findOne(id);
			calendar.updateDate(schedule);
			System.out.println("쳌 : " + calendar);
			calendarRepository.save(calendar);
			entity = new ResponseEntity<String>("SUCCEUSS", HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@PostMapping("")
	public ResponseEntity<String> create(@RequestBody Calendar schedule) {
		ResponseEntity<String> entity = null;
		try {
			System.out.println("일정 생성");
			calendarRepository.save(schedule);
			entity = new ResponseEntity<String>("SUCUESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
