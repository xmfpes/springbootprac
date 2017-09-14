package org.kyu.web;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.kyu.lotto.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/lotto")
public class LottoController {
	static LottoMachine lottoMachine;
	static WinningLotto winningLottoCreator;
	@GetMapping("")
	public String Lotto() {
		
		return "/data/lotto";
	}
	@GetMapping("/result")
	public String LottoResult(int money, Model model) throws IOException {
		
		lottoMachine = new LottoMachine();
		winningLottoCreator = new WinningLotto();
		Lotto winningLotto = winningLottoCreator.getWinningLottor();
		int countOfLotto = lottoMachine.countOfLotto(money);
		List<Lotto> buyngLottos = lottoMachine.buyAutoLottos(countOfLotto);
		Result result = lottoMachine.matchLotto(winningLotto, buyngLottos);
		LottoReport lottoReport = new LottoReport(money, result);
		
		System.out.println("hi man");
		model.addAttribute("lottoresult", result);
		model.addAttribute("mylotto", buyngLottos);
		
		System.out.println("bye man");
		
		return "/data/result";
	}
}
