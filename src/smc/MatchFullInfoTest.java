package smc;

import helper.DataUrls;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class MatchFullInfoTest {
	
	public static void getMatchFullInfo(String matchId) throws Exception{
		String url = DataUrls.ft_matchInfo;
		
		HttpRequester request = new HttpRequester();
//		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(url + matchId);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
//			 System.out.println(json);
		}
		
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		int innerCode1 = data.getInt("innerCode");
		String homeTeamName = data.getString("homeTeamName");
		String awayTeamName = data.getString("awayTeamName");
		System.out.println(matchId + " " + homeTeamName + " VS " + awayTeamName);
		if (innerCode1 == 0) {
			System.out.println(" 没有数据情报数据");
		} else {
			
			JSONObject meetingBo = data.getJSONObject("meetingBo");
			int innerCode2 = meetingBo.getInt("innerCode");
			if (innerCode2 == 0) {
				System.out.println("没有交战数据");
			} else {
				String homeWinRate = meetingBo.getString("homeWinRate");
				String awayWinRate = meetingBo.getString("awayWinRate");
				if (checkRate(homeWinRate, awayWinRate)) {
					System.err.println("交战概率有误");
					System.err.println(homeWinRate + " " + awayWinRate + "\n");
				}
			}
			
			JSONObject fightingBo = data.getJSONObject("fightingBo");
			
			JSONObject fightingHBo = fightingBo.getJSONObject("fightingHBo");
			String hwin = fightingHBo.getString("winRate");
			int innerCode3 = fightingHBo.getInt("innerCode");
			if (innerCode3 == 0) {
				System.out.println("主队没有战力数据");
			}
			
			JSONObject fightingABo = fightingBo.getJSONObject("fightingABo");
			String awin = fightingABo.getString("winRate");
			int innerCode4 = fightingABo.getInt("innerCode");
			if (innerCode4 == 0) {
				System.out.println("客队没有战力数据");
			}
			if (checkRate(hwin, awin)) {
				System.err.println("战力概率有误");
				System.err.println(hwin + " " + awin + "\n");
			}
			
			JSONObject recentBo = data.getJSONObject("recentBo");
			JSONObject recentHBo = recentBo.getJSONObject("recentHBo");
			hwin = recentHBo.getString("winRate");
			int innerCode5 = recentHBo.getInt("innerCode");
			if (innerCode5 == 0) {
				System.out.println("主队没有状态伤停数据");
			}
			JSONObject recentABo = recentBo.getJSONObject("recentABo");
			awin = recentABo.getString("winRate");
			int innerCode6 = recentABo.getInt("innerCode");
			if (innerCode6 == 0) {
				System.out.println("客队没有状态伤停数据");
			}
			if (checkRate(hwin, awin)) {
				System.err.println("战力概率有误");
				System.err.println(hwin + " " + awin);
			}
			
			JSONObject oddsBo = data.getJSONObject("oddsBo");
			int innerCode7 = oddsBo.getInt("innerCode");
			if (innerCode7 == 0 ) {
				System.out.println("沒有赔率必发数据");
			}
			
			String homeWinRate = oddsBo.getString("homeWinRate");
			String awayWinRate = oddsBo.getString("awayWinRate");
			
			if (checkRate(homeWinRate, awayWinRate)) {
				System.err.println("赔率必发概率有误");
				System.err.println(homeWinRate + " " + awayWinRate + "\n");
			}
		}
		System.out.println();
	}
	
	public static boolean checkRate(String homeWinRate, String awayWinRate){
		int home = Integer.valueOf(homeWinRate);
		int away = Integer.valueOf(awayWinRate);
		int value = home-away;
		value = Math.abs(value);
		if (home > 60 || away > 60 || home < 40 || away < 40 || (home + away) != 100) {
			return true;
		} else {
			return false;
		}
	}
	

	public static void main(String[] args) throws Exception {
		String issues = JzIssue.getJZIssues();
		if (!issues.equals("")) {
			String [] issue = issues.split(",");
			for (int i = 0; i < issue.length; i++) {
				// 获取对阵
				String matchs = JzMatchs.getJZMatchs(issue[i]);
				if (! matchs.equals("")) {
					String [] match = matchs.split(",");
					for (int j = 0; j < match.length; j++) {
						getMatchFullInfo(match[j]);
					}
				}
			}
		}
	}

}
