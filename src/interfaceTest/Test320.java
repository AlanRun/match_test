package interfaceTest;

import helper.AppReq;
import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test320 {
	
	public static void get320(String issue) throws Exception{
		
		String url = DataUrls.url_bd;
		String params = DataUrls.params_320;
		String suc = "code\"=0";
		String bParams = "";
		
		if (!issue.equals("")) {
			bParams = "date," + issue;
		}
		String hParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			JSONArray matches = data.getJSONArray("matches");
			
			for (int i = 0; i < matches.size(); i++) {
				JSONObject match = (JSONObject) matches.get(i);
				
				String jzNum = match.getString("jzNum");
				String NMm = match.getString("uniqueTournamentName");
				String matchTime = match.getString("matchTime");
				String homeTeamName = match.getString("homeTeamName");
				String awayTeamName = match.getString("awayTeamName");
				String SpSPF = match.getString("sPF");

				String matchId = match.getString("matchId");
				String isFinish = match.getString("isFinish");
				String isHit = match.getString("isHit");
				
			}
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		String issue = "2017-11-30";
		get320(issue);
	}

}
