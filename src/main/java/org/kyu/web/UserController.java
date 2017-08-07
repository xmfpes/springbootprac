package org.kyu.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.kyu.domain.User;
import org.kyu.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @RequestParam User updateUser, HttpSession session) {
		System.out.println("put user : " + updateUser);
		Object tempUser = session.getAttribute("sessionedUser");
		if(tempUser == null) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = (User)tempUser;
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		User user = userRepository.findOne(id);
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("/{id}/update")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");
		if(tempUser == null) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = (User)tempUser;
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		model.addAttribute("user", userRepository.findOne(sessionedUser.getId()));
		return "/user/updateForm";
	}
	
	@GetMapping("/{id}")
	public String showProfile(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "/user/profile";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			System.out.println("login failed");
			return "redirect:/users/loginForm";
		}
		if(!password.equals(user.getPassword())) {
			System.out.println("login failed");
			return "redirect:/users/loginForm";
		}
		
		session.setAttribute("sessionedUser", user);
		
		System.out.println("login success");
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}
	
	@PostMapping("")
	public String create(User user){
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
}
