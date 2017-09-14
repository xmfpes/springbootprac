package org.kyu.lotto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Lotto {
	private List<Integer> lottoNos;
	private boolean isBonus;
	
	public Lotto() {
		lottoNos = new ArrayList<Integer>();
		isBonus = false;
	}
	public Lotto(List<Integer> lotto) {
		lottoNos = lotto;
		isBonus = false;
	}
	public Lotto(Integer data[]) {
		lottoNos = new ArrayList<Integer>();
		isBonus = false;
		createLotto(data);
	}
	public Integer findSingleLotto(int index){
		return lottoNos.get(index);
	}
	
	public void createLotto(Integer data[]) {
		List<Integer> temp = new ArrayList<>(Arrays.asList(data));
		Collections.shuffle(temp);
		lottoNos = temp.subList(0, 6);
		Collections.sort(lottoNos);
		Collections.unmodifiableList(lottoNos);
	}
	
	public int compareLotto(Lotto winningLotto) {
		int count = 0;
		ArrayList<Integer> tempLotto = new ArrayList<>(this.lottoNos);
		tempLotto.retainAll(winningLotto.getLottoNos());
		count = tempLotto.size();
		if(count == 5 && isBonus) {
			return 7;
		}
		return count;
	}
	
	public void checkBonus(int bonusNum) {
		if(lottoNos.contains(bonusNum)) {
			this.isBonus = true;
		}
	}
	public List<Integer> getLottoNos() {
		return lottoNos;
	}
	public void setLottoNos(List<Integer> lottoNos) {
		this.lottoNos = lottoNos;
	}
	public boolean isBonus() {
		return isBonus;
	}
	public void setBonus(boolean isBonus) {
		this.isBonus = isBonus;
	}
	
}
