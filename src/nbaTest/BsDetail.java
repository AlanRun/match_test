package nbaTest;

import java.util.Date;

import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;
import utils.LogWrite;

public class BsDetail {
	static String url = "https://smc.jdddata.com/api/scoredetail/bslive?lotteryId=91&pcode=h5&pts=0&version=v2.1&matchid=";
	private static String bs_url = DataUrls.bs_match;
	
	public static String getBsStatue(String match) throws Exception{
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(url + match);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("json is empty");
		} else {
//			System.out.println(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
//		String statusName = data.getString("statusName");
		String statusString = data.getString("statusString");
		String homeTeamScore = data.getString("homeTeamScore");
		String awayTeamScore = data.getString("awayTeamScore");
		String result = statusString + " " + awayTeamScore + ":" + homeTeamScore;
		return result; 
	}
	
	public static void checkSpExist() throws Exception{
		String url1 = "https://saishi.jdd.com/ajax/soccer.ashx?pcode=h5&version=v1.0.0&cmsm=asianOddsCallback&action=asianodds&isNBA=1&lotteryId=91&matchid=968635";
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(url1);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
//			System.out.println(json);
		}
		
		Date d = new Date();
		if (json.contains(":[],")) {
			System.out.println(json);
			System.out.println(d.toString() + " [968635 asianodds empty!]");
			LogWrite.saveToFile(json);
			LogWrite.saveToFile(" [968635 asianodds empty!]");
		} else {
			System.out.println(d.toString() + " [968635 asianodds normal}");
			LogWrite.saveToFile(" [968635 asianodds normal}");
		}
	}
	
	public static String compareListAndDetailMatchTime(String issue) throws Exception {

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(bs_url + issue);

		String matchs = "";

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return matchs;
		} else {
//			System.out.println(json);
		}
		
		JSONObject obj = JSONObject.fromObject(json);
		JSONArray MatchIssue = obj.getJSONArray("MatchIssue");
		
		JSONObject Missue = (JSONObject) MatchIssue.get(0);
		JSONArray Matchs = Missue.getJSONArray("Matchs");
		for (int i = 0; i < Matchs.size(); i++) {
			JSONObject Match = (JSONObject) Matchs.get(i);
			String matchId = Match.getString("MatchId");
			int StatusCode = Match.getInt("StatusCode");
			if (matchId.equals("968636")) {
				String HomeScore = Match.getString("HomeScore");
				String AwayScore = Match.getString("AwayScore");
				String ListStatus = Match.getString("StatusString") +  " " + AwayScore + ":" + HomeScore;
				
				String DetailStatus = getBsStatue(matchId);
				Date d = new Date();
				if (ListStatus.equals(DetailStatus)) {
					System.out.println(d.toString() + " 时间比分一致：列表=" + ListStatus + "\t详情=" + DetailStatus);
				} else {
					System.err.println(d.toString() + " 时间比分不同：列表=" + ListStatus + "\t详情=" + DetailStatus);
				}
			}
			matchs = matchs + matchId + ",";
		}
		
		matchs = matchs.substring(0, matchs.length() -1);
		return matchs;
	}

	public static void main(String[] args) throws Exception {
//		checkSpExist();
		for (int i = 0; i < 10000; i++) {
			compareListAndDetailMatchTime("2017-10-17");
			Thread.sleep(2000);
		}
	}

}
