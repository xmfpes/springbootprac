package org.kyu.lotto;

import java.util.ArrayList;

public class Result {
	private int countOfMatch3;
	private int countOfMatch4;
	private int countOfMatch5;
	private int countOfMatch6;
	private int countOfMatchBonus;
	private int sumOfLottoMoney;

	public Result() {}
	public Result(int n) {
		if (n == 3) {
			countOfMatch3 = 1;
			sumOfLottoMoney = 5000;
		}
		if (n == 4) {
			countOfMatch4 = 1;
			sumOfLottoMoney = 50000;
		}
		if (n == 5) {
			countOfMatch5 = 1;
			sumOfLottoMoney = 1500000;
		}
		if (n == 6) {
			countOfMatch6 = 1;
			sumOfLottoMoney = 1000000000;
		}
		if (n == 7) {
			countOfMatchBonus = 1;
			sumOfLottoMoney = 100000000;
		}
	}

	public Result(int countOfMatch3, int countOfMatch4, int countOfMatch5, int countOfMatchBonus, int countOfMatch6, int sumOfLottoMoney) {

		this.countOfMatch3 = countOfMatch3;
		this.countOfMatch4 = countOfMatch4;
		this.countOfMatch5 = countOfMatch5;
		this.countOfMatchBonus = countOfMatchBonus;
		this.countOfMatch6 = countOfMatch6;
		this.sumOfLottoMoney = sumOfLottoMoney;

	}

	public Result updateResult(Result result) {
		return new Result(this.countOfMatch3 + result.getCountOfMatch3(),
				this.countOfMatch4 + result.getCountOfMatch4(), this.countOfMatch5 + result.getCountOfMatch5(),
				this.countOfMatchBonus + result.getCountOfMatchBonus(), this.countOfMatch6 + result.getCountOfMatch6(),
				this.sumOfLottoMoney + result.sumOfLottoMoney);
	}

	public int getCountOfMatch3() {
		return countOfMatch3;
	}

	public void setCountOfMatch3(int countOfMatch3) {
		this.countOfMatch3 = countOfMatch3;
	}

	public int getCountOfMatch4() {
		return countOfMatch4;
	}

	public void setCountOfMatch4(int countOfMatch4) {
		this.countOfMatch4 = countOfMatch4;
	}

	public int getCountOfMatch5() {
		return countOfMatch5;
	}

	public void setCountOfMatch5(int countOfMatch5) {
		this.countOfMatch5 = countOfMatch5;
	}

	public int getCountOfMatch6() {
		return countOfMatch6;
	}

	public void setCountOfMatch6(int countOfMatch6) {
		this.countOfMatch6 = countOfMatch6;
	}

	public int getCountOfMatchBonus() {
		return countOfMatchBonus;
	}

	public void setCountOfMatchBonus(int countOfMatchBonus) {
		this.countOfMatchBonus = countOfMatchBonus;
	}
	public int getSumOfLottoMoney() {
		return sumOfLottoMoney;
	}
	public void setSumOfLottoMoney(int sumOfLottoMoney) {
		this.sumOfLottoMoney = sumOfLottoMoney;
	}

}
