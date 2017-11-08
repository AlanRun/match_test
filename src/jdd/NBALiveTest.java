package jdd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import helper.NBALive;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.ConfigProperty;
import utils.HttpRequester;
import utils.HttpRespons;

public class NBALiveTest {
	
	private static String URL = "http://dcds.jdd.com/Api.Basket/Ajax/NBALive.ashx";
	
	static Map<String, String> params= new HashMap<String, String>();
	
	public static ArrayList<NBALive> getNBAMatchs(String mid) throws Exception {

		ArrayList<NBALive> list = new ArrayList<NBALive>();
		params.put("cmsm", "dz");
		params.put("action", "match");
		params.put("issue", mid);
		params.put("pcode", "android");
		params.put("lotteryId", "91");
		params.put("playId", "9101");
		params.put("pts", "0");
		params.put("version", "v3.0");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL, params);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return list;
		} else {
//			System.out.println(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray IssueArray = obj.getJSONArray("MatchIssue");
		JSONObject Issue = IssueArray.getJSONObject(0);
		JSONArray Matchs = Issue.getJSONArray("Matchs");
		
		for (int i = 0; i < Matchs.size(); i++) {
			JSONObject match = Matchs.getJSONObject(i);
			NBALive NBA = new NBALive();
			
			int MatchId = match.getInt("MatchId");
			String ISSUE = match.getString("Issue");
			boolean IsNBA = match.getBoolean("IsNBA");
			int StatusCode = match.getInt("StatusCode");
			
			String HomeTeam = match.getString("HomeTeam");
			String AwayTeam = match.getString("AwayTeam");
			String StandardMatchDate = match.getString("StandardMatchDate");
			String StatusString = match.getString("StatusString");
			
			String Score1 = match.getString("Score1");
			String Score2 = match.getString("Score2");
			String Score3 = match.getString("Score3");
			String Score4 = match.getString("Score4");
			String Score5 = match.getString("Score5");
			int AwayScore = match.getInt("AwayScore");
			int HomeScore = match.getInt("HomeScore");
			
			NBA.setIssue(ISSUE);
			NBA.setMatchId(MatchId);
			NBA.setIsNBA(IsNBA);
			NBA.setStatusCode(StatusCode);
			NBA.setScore1(Score1);
			NBA.setScore2(Score2);
			NBA.setScore3(Score3);
			NBA.setScore4(Score4);
			NBA.setScore5(Score5);
			NBA.setAwayScore(AwayScore);
			NBA.setHomeScore(HomeScore);
			NBA.setAwayTeam(AwayTeam);
			NBA.setHomeTeam(HomeTeam);
			NBA.setStandardMatchDate(StandardMatchDate);
			NBA.setStatusString(StatusString);
			
			System.out.println(NBA.toString());
			
			if (StatusCode == 0) {
				assertTrue("比赛未开始，比分异常", Score1.equals("") && Score2.equals("") && Score3.equals("")
						&& Score4.equals("") && Score5.equals("") && AwayScore == 0 && HomeScore == 0);
			} else if (StatusCode == 1) {
				assertTrue("比赛第一节，比分异常", Score2.equals("") && Score3.equals("")
						&& Score4.equals("") && Score5.equals(""));
			} else if (StatusCode == 2) {
				assertTrue("比赛第一节，比分异常", (!Score1.equals("")) && Score2.equals("") && Score3.equals("")
						&& Score4.equals("") && Score5.equals("") && AwayScore != 0 && HomeScore != 0);
			} else if (StatusCode == 3) {
				assertTrue("比赛第二节，比分异常", (!Score1.equals("")) && Score3.equals("")
						&& Score4.equals("") && Score5.equals("") && AwayScore != 0 && HomeScore != 0);
			} else if (StatusCode == 4) {
				assertTrue("比赛第二节，比分异常", (!Score1.equals("")) && (!Score2.equals("")) && Score3.equals("")
						&& Score4.equals("") && Score5.equals("") && AwayScore != 0 && HomeScore != 0);
			} else if (StatusCode == 5) {
				assertTrue("比赛第三节，比分异常", (!Score1.equals("")) && (!Score2.equals(""))
						&& Score4.equals("") && Score5.equals("") && AwayScore != 0 && HomeScore != 0);
			} else if (StatusCode == 6) {
				assertTrue("比赛第三节，比分异常", (!Score1.equals("")) && (!Score2.equals("")) && (!Score3.equals(""))
						&& Score4.equals("") && Score5.equals("") && AwayScore != 0 && HomeScore != 0);
			} else if (StatusCode == 7) {
				assertTrue("比赛第四节，比分异常", (!Score1.equals("")) && (!Score2.equals("")) && (!Score3.equals(""))
						&& AwayScore != 0 && HomeScore != 0);
			} else if (StatusCode < 12) {
				assertTrue("比赛第四节，比分异常", (!Score1.equals("")) && (!Score2.equals("")) && (!Score3.equals(""))
						&& (!Score4.equals("")) && AwayScore != 0 && HomeScore != 0);
			}
			
			list.add(NBA);
		}
		return list;
	}
	
	public static void assertTrue(String msg, boolean flag){
		Date d = new Date();
		if (flag) {
//			System.out.println("" + d);
		} else {
			System.err.println("" + d + "," + msg);
		}
	}
	
	/**
	 * get property from config.properties
	 */
	public static String getConfigProperty(String key) {
		ConfigProperty cp = new ConfigProperty("config.properties");
		String value = cp.getProperty(key);

		return value;
	}

	public static void main(String[] args) throws Exception {
		String issue = getConfigProperty("issue");
		String gap = getConfigProperty("gap");
		
		for (int i = 0; i < 2200; i++) {
			System.out.println("check 第 " + (i + 1) + " 次");
			getNBAMatchs(issue);
			Thread.sleep(10 * 1000);
		}
	}

}
