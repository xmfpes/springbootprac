package org.kyu.web;

import org.kyu.domain.QuestionRepository;
import org.kyu.domain.User;
import org.kyu.domain.UserRepository;
import org.kyu.util.HttpSessionUtils;

import javax.servlet.http.HttpSession;

import org.kyu.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/form";
		}
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionedUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/qna";
	}
	
	@GetMapping("/{id}")
	public String read(@PathVariable Long id, Model model) {
		model.addAttribute("qna", questionRepository.findOne(id));
		return "qna/show";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User SessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(id);
		
		if(!question.isCurrentWriter(SessionedUser)) {
			throw new IllegalStateException("자신의 글만 수정할 수 있습니다.");
		}
		question.updateQuestion(title, contents);
		questionRepository.save(question);
		return "redirect:/qna/" + question.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User SessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(id);
		
		if(!question.isCurrentWriter(SessionedUser)) {
			throw new IllegalStateException("자신의 글만 삭할 수 있습니다.");
		}
		questionRepository.delete(id);
		return "redirect:/qna";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, HttpSession session, Model model) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User SessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(id);
		
		if(!question.isCurrentWriter(SessionedUser)) {
			System.out.println(question);
			System.out.println("zz" + SessionedUser);
			throw new IllegalStateException("자신의 글만 수정할 수 있습니다.");
		}
		System.out.println(question.getTitle());
		System.out.println(question.getContents());
		model.addAttribute("question", question);
		
		return "qna/updateForm";
	}
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		return "qna/form";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("qnas", questionRepository.findAll());
		return "qna/list";
	}
	
	
}
