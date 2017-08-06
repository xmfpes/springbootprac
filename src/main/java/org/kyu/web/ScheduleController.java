package org.kyu.web;

import java.awt.Color;

import org.kyu.domain.Calendar;
import org.kyu.domain.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private CalendarRepository calendarepository;
	
	@GetMapping("")
	public String show() {
		return "schedule/schedule";
	}
	@PostMapping("")
	public void create(@RequestBody Calendar schedule)
	{
		System.out.println("확인확");
		System.out.println("ddddd" + schedule);
		calendarepository.save(schedule);
	}
}
