package smc;

import java.util.ArrayList;
import java.util.Date;

import helper.DataUrls;
import helper.JzMatchSpInfo;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class HeadTest {
	static Date d = new Date();

	// 竞足头部接口
	private static String url = DataUrls.ft_head;

	static ArrayList<String> list = new ArrayList<String>();

	public static String getTeamName(String matchId) throws Exception {
		String URL = url + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		String homeTeamName = data.getString("homeTeamName");
		String awayTeamName = data.getString("awayTeamName");
		String unName = data.getString("uniqueTournamentName");
		String matchTime = data.getString("matchTime");
		
		JzMatchSpInfo matchInfo = new JzMatchSpInfo();
		matchInfo.setMatchID(matchId);
		matchInfo.setHTeam(homeTeamName);
		matchInfo.setVTeam(awayTeamName);
		
		String result = matchId + "\t" + unName + "\t" + homeTeamName + "\t" + awayTeamName + "\t" + matchTime;
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) throws Exception {

		// 获取期次
		String issues = JzIssue.getJZIssues();
		if (!issues.equals("")) {
			String[] issue = issues.split(",");
			for (int i = 0; i < issue.length; i++) {
				// 获取对阵
				String matchs = JzMatchs.getJZMatchs(issue[i]);
				if (!matchs.equals("")) {
					String[] match = matchs.split(",");
					for (int j = 0; j < match.length; j++) {
						getTeamName(match[j]);
					}
				}
			}
		}
	}
}
