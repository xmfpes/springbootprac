package org.kyu.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.kyu.domain.Calendar;
import org.kyu.domain.CalendarRepository;
import org.kyu.domain.User;
import org.kyu.domain.UserRepository;
import org.kyu.exception.CustomResponseEntityExceptionHandler;
import org.kyu.util.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
	
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@Autowired
	private CalendarRepository calendarRepository;
	
	//Create(Insert)
	@PostMapping("")
	public ResponseEntity<Calendar> create(@RequestBody Calendar schedule, HttpSession session) {
		ResponseEntity<Calendar> entity = null;
		if(!HttpSessionUtils.isLoginUser(session)) {
			entity = new ResponseEntity<Calendar>(HttpStatus.BAD_REQUEST);
			return entity;
		}
		logger.info("일정 생성");
		schedule.setWriter((User) (session.getAttribute(HttpSessionUtils.USER_SESSION_KEY)));
		Calendar dbCalendar = calendarRepository.save(schedule);
		logger.info("insert id : " + dbCalendar.getId());
		entity = new ResponseEntity<Calendar>(dbCalendar, HttpStatus.CREATED);	
		
		return entity;
	}
	
	//R
	@GetMapping("/{id}")
	public ResponseEntity<List<Calendar>> load(@PathVariable Long id) {
		ResponseEntity<List<Calendar>> entity = null;
		List<Calendar> datalist = calendarRepository.findByWriterId(id);
		for(int i=0; i<datalist.size(); i++) {
			System.err.println(datalist.get(i));
		}
		entity = new ResponseEntity< List<Calendar>>(datalist, HttpStatus.OK);
		return entity;
	}
	
	//U
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Calendar schedule) {
		ResponseEntity<String> entity = null;
		try {
			logger.info("일정 업데이트");
			Calendar calendar = calendarRepository.findOne(id);
			calendar.updateDate(schedule);
			calendarRepository.save(calendar);
			entity = new ResponseEntity<String>("SUCCEUSS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	//D
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		ResponseEntity<String> entity = null;
		try {
			logger.info("일정 삭제");
			calendarRepository.delete(id);
			entity = new ResponseEntity<String>("SUCCEUSS", HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
