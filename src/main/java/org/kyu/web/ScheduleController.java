package org.kyu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
	
	@GetMapping("/schedule")
	public String show() {
		return "schedule/schedule";
	}
}
