package org.kyu.web;

import org.kyu.domain.QnaRepository;
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
public class QnaController {
	
	@Autowired
	private QnaRepository qnarepository;
	
	@GetMapping("/form")
	public String form() {
		return "qna/form";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("qna", qnarepository.findOne(id));
		return "qna/show";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("qnas", qnarepository.findAll());
		return "qna/list";
	}
	
	@PostMapping("")
	public String create(Question qus) {
		qnarepository.save(qus);
		return "redirect:/qna";
	}
}
