package org.kyu.baseball;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class BaseBallCalculator {
	ArrayList<Integer> computerlist;
	ArrayList<Integer> userlist;

	public ArrayList<Integer> inputUserBalls(String inputvalue) {
		String [] values= inputvalue.split(" ");
		ArrayList<Integer> userlist = new ArrayList<Integer>();
		for (String val : values) {
			userlist.add(Integer.parseInt(val));
			System.out.println("user ball : " + val);
		}
		return userlist;
	}
	
	public Result calculateBall(ArrayList<Integer> computerlist, int userBall, int position) {
		Result result = new Result();
		if(computerlist.get(position) == userBall) {
			result.addStrike();
		}
		if(computerlist.contains(userBall)) {
			result.addBall();
		}
		return result;
	}
	public String calculateBalls(ArrayList<Integer> computerlist, ArrayList<Integer> userlist) {
		Result result = new Result();
		for(int i=0; i<computerlist.size(); i++) {
			result.getResult(calculateBall(computerlist, userlist.get(i), i));
		}
		if(result.getStrike() > 0) {
			return result.getStrike() + " Strike!! ";
		}
		if(result.getBall() > 0) {
			return result.getBall() + " ball";
		}
		return "noting.....";
	}

	public ArrayList<Integer> generateComputerBalls() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			list.add((i));
		}
		Collections.shuffle(list);
		for (int i = 0; i < 6; i++) {
			list.remove(0);
			
		}
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		return list;
	}
}
