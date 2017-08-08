package org.kyu.web;

import org.kyu.domain.QuestionRepository;
import org.kyu.domain.User;

import javax.servlet.http.HttpSession;

import org.kyu.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionrepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		return "qna/form";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("qna", questionrepository.findOne(id));
		return "qna/show";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("qnas", questionrepository.findAll());
		return "qna/list";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionedUser.getUserId(), title, contents);
		questionrepository.save(newQuestion);
		return "redirect:/qna";
	}
}
