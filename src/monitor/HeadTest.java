package monitor;

import java.util.ArrayList;
import java.util.Date;

import helper.FootballMatch;
import net.sf.json.JSONObject;
import smc.JzIssue;
import smc.JzMatchs;
import utils.HttpRequester;
import utils.HttpRespons;

/**
 * 监测smc head接口是否正常，
 * 
 * @author Alan Huang
 *
 */
public class HeadTest {
	static Date d = new Date();

	// 竞足头部接口
	private static String url_common = "https://smc.jdd.com/api/scoredetail/head?lotteryId=90&pcode=h5&pts=0&version=v2.1&matchid=";

	public static void checkHead(FootballMatch match) throws Exception {

		String URL = url_common + match.getMId();

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.err.println(match.getNUM() + " " + match.getMId() + " respons is empty!");
			return;
		} else {

		}

		try {
			JSONObject obj = JSONObject.fromObject(json);
			int code = obj.getInt("code");
			if (code == -1) {
				System.out.println(match.getNUM() + " " + match.getMId() + " head error");
			} else if (code == 0) {
				System.out.println(match.getNUM() + " " + match.getMId() + " head normal");
			}

		} catch (Exception e) {
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		Date d = new Date();
		System.out.println(d.toString());

		// 获取期次
		String issues = JzIssue.getJZIssues();
		if (!issues.equals("")) {
			String[] issue = issues.split(",");
			for (int i = 0; i < issue.length; i++) {
				// 获取对阵
				System.out.println("=========" + issue[i] + "=========");
				ArrayList<FootballMatch> list = JzMatchs.getMatchList(issue[i]);
				for (int j = 0; j < list.size(); j++) {
					FootballMatch match = list.get(j);
					checkHead(match);
				}
				System.out.println("=========" + issue[i] + "=========\n");
			}
		}
	}
}
