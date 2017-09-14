package org.kyu.lotto;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;

public class Main {
	static LottoMachine lottoMachine;
	static WinningLotto winningLottoCreator;
		
	public static void main(String[] args) throws IOException {
		lottoMachine = new LottoMachine();
		winningLottoCreator = new WinningLotto();
		Lotto winningLotto = winningLottoCreator.getWinningLottor();
		Scanner sc = new Scanner(System.in);
		int money = lottoMachine.getBuyingMoney(sc);
		int countOfLotto = lottoMachine.countOfLotto(money);
		List<Lotto> buyngLottos = lottoMachine.buyAutoLottos(countOfLotto);
		Result result = lottoMachine.matchLotto(winningLotto, buyngLottos);
		LottoReport lottoReport = new LottoReport(money, result);
		lottoReport.showLottoReport();
		}
}
