package org.kyu.lotto;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class WinningLotto {
	private Lotto lotto;
	private int bonusNum;
	public WinningLotto() {
		lotto = new Lotto();
	}
	public Lotto getWinningLottor() throws IOException{
		Connection.Response response = Jsoup.connect("http://www.nlotto.co.kr/common.do?method=main")
                .method(Method.GET)
                .execute();
		Document googleDocument = response.parse();
		Elements elements = googleDocument.select("p[id=numView]").first().children();
		return parseHTML(elements);
	}
	
	private Lotto parseHTML(Elements elements) {
		List<Integer> winningLotto = new ArrayList<Integer>();
		for(int i=0; i<elements.size() - 2; i++) {
			winningLotto.add(Integer.parseInt(elements.get(i).attr("alt")));
		}
		this.bonusNum = Integer.parseInt(elements.get(elements.size()-1).attr("alt"));
		return new Lotto(winningLotto);
	}
}
