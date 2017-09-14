package org.kyu.lotto;

public class LottoReport {
	static final String countOfMatch3 = "3개 일치 (5000원)-";
	static final String countOfMatch4 = "4개 일치 (50000원)-";
	static final String countOfMatch5 = "5개 일치 (1500000원)-";
	static final String countOfMatch6 = "6개 일치 (1000000000원)-";
	static final String countOfMatchBonus = "5개 일치, 보너스 볼 일 (100000000원)-";
	static final String sumOfLotto = "총 수익률은 ";
	
	private Result result;
	private int money;
	
	public LottoReport(int money, Result result) {
		this.result = result;
		this.money = money;
	}
	void showLottoReport() {
		System.out.println(countOfMatch3 + result.getCountOfMatch3());
		System.out.println(countOfMatch4 + result.getCountOfMatch4());
		System.out.println(countOfMatch5 + result.getCountOfMatch5());
		System.out.println(countOfMatchBonus + result.getCountOfMatchBonus());
		System.out.println(countOfMatch6 + result.getCountOfMatch6());
		System.out.println(sumOfLotto + ((double)result.getSumOfLottoMoney()/money) * 100 + "%" );
	}
}
