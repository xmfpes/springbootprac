package org.kyu.web;

import java.util.ArrayList;

import org.kyu.baseball.BaseBallCalculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseBallController {
	BaseBallCalculator baseball = new BaseBallCalculator();
	ArrayList<Integer> computerBalls;
	@GetMapping("/baseball")
	public ModelAndView input(String baseBall) {
		
		if(computerBalls == null)
			computerBalls = baseball.generateComputerBalls();
		
		ArrayList<Integer> userBalls = baseball.inputUserBalls(baseBall);
		
		String result = baseball.calculateBalls(computerBalls, userBalls);
		
		ModelAndView mav = new ModelAndView("baseball/result");
		mav.addObject("result", result);
		
		
		return mav;
	}
	
	@GetMapping("/newgame")
	public ModelAndView newgamr() {
		computerBalls = baseball.generateComputerBalls();
		
		ModelAndView mav = new ModelAndView("redirect:/input");
		
		return mav;
	}
	@GetMapping("/input")
	public String baseBall() {
		return "baseBall/baseBall";
	}
}
