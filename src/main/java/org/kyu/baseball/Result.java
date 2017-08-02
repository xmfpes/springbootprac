package org.kyu.baseball;

public class Result {
	private int strike = 0;
	private int ball = 0;
	public int getStrike() {
		return strike;
	}
	public void setStrike(int strike) {
		this.strike = strike;
	}
	public int getBall() {
		return ball;
	}
	public void setBall(int ball) {
		this.ball = ball;
	}
	public void addBall() {
		this.ball += 1;
	}
	public void addStrike() {
		this.strike += 1;
	}
	public void getResult(Result result) {
		this.strike += result.getStrike();
		this.ball += result.getBall();
	}
}
