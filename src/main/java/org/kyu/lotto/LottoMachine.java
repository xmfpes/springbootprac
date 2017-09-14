package org.kyu.lotto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LottoMachine {
	private Integer data[];
	
	public LottoMachine() {
		data = new Integer[46];
		for (int i = 0; i < 46; i++) {
			data[i] = i + 1;
		}
	}

	public int getBuyingMoney(Scanner sc) {
		return sc.nextInt();
	}

	public int countOfLotto(int money) {
		return money / 1000;
	}

	public Lotto buyAutoLotto() {
		return new Lotto(data);
	}

	public List<Lotto> buyAutoLottos(int countOfLotto) {
		List<Lotto> myLottolist = new ArrayList<Lotto>();
		for (int i = 0; i < countOfLotto; i++) {
			myLottolist.add(buyAutoLotto());
		}
		return myLottolist;
	}
	
	public Result matchLotto(Lotto winningLotto, List<Lotto> buyngLottos) {
		Result result = new Result();
		for(int i=0; i<buyngLottos.size(); i++) {
			result = result.updateResult(
					(new Result(winningLotto.compareLotto(buyngLottos.get(i))))
			);
		}
		return result;
	}
	
	
}
