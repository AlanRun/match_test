package smc;

import java.util.ArrayList;
import java.util.Date;

import helper.JzMatchSpInfo;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class YuCeTest {
	static Date d = new Date();

	// 竞足头部接口
	private static String url_common = "http://smc.jdd.com/api/scoredetail/head?lotteryId=90&pcode=h5&pts=0&version=v2.1&matchid=";
	// 需要显示预测的赛事ID
	private static String uniqueTournamentIds = "17,242,131,8,37,480,498,11,18,384,34,35,36,136,182,373,23,155,335,851,1598,323,20,325,308,463,217,203,238,244,402,679,44,7,352,333,38,24,196";

	static ArrayList<String> list = new ArrayList<String>();

	public static String checkYuceInfo(String matchId) throws Exception {
		setUniqueTournamentIds();
		
		String URL = url_common + matchId;
		String yuce = "";
		String result = "";

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			result = "null";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		String isShowYuCe = data.getString("isShowYuCe");
		String uniqueTournamentName = data.getString("uniqueTournamentName");
		String uniqueTournamentId = data.getString("uniqueTournamentId");
		String homeTeamName = data.getString("homeTeamName");
		String awayTeamName = data.getString("awayTeamName");
		
		JzMatchSpInfo matchInfo = new JzMatchSpInfo();
		matchInfo.setMatchID(matchId);
		matchInfo.setHTeam(homeTeamName);
		matchInfo.setVTeam(awayTeamName);
		
		yuce = matchId + "\t" + homeTeamName + "\t" + awayTeamName + "\t" + uniqueTournamentName + "\t" + isShowYuCe;

		// 判断赛事是否需要显示预测
		if (list.contains(uniqueTournamentId)) {
			if (isShowYuCe.equals("N")) {
				isShowYuCe = "赛事无预测";
				result = "null";
			}
		} else {
			if (isShowYuCe.equals("Y")) {
				isShowYuCe = "赛事有预测";
			}
		}

		if (isShowYuCe.equals("Y")) {
			request = new HttpRequester();
			request.setDefaultContentEncoding("UTF-8");

			// 预测接口
			hr = request.sendGet(
					"http://forecast.databiger.com/odds/forecast/getSsMatchAnalyzeInfo?version=v2.1&pts=0&pcode=h5&lotteryId=90&platform=trade&matchId="
							+ matchId);
			json = hr.getContent();
			if (json == null || json.equals("")) {
				return "null";
			} else {

			}
			obj = JSONObject.fromObject(json);
			data = obj.getJSONObject("data");
			int code = obj.getInt("code");

			// 判断预测接口是否正常
			if (code != 1) {
				isShowYuCe = "预测异常";
			} else {
				JSONObject jddBo = data.getJSONObject("jddBo");
//				int innerCode = Integer.valueOf(jddBo.getString("innerCode"));
//				if (innerCode != 1) {
//					isShowYuCe = "奖多多预测暂无数据";
//					result = "null";
//				}

				int winPercent = Integer.valueOf(jddBo.getString("winPercent"));
				int drawPercent = Integer.valueOf(jddBo.getString("drawPercent"));
				int lossPercent = Integer.valueOf(jddBo.getString("lossPercent"));

				// 0 <=任意胜率 <= 90，则显示奖多多预测
				if ((90 > winPercent && winPercent > 0) || (90 > drawPercent && drawPercent > 0)
						|| (90 > lossPercent && lossPercent > 0)) {
					result = winPercent + "," + drawPercent + "," + lossPercent;
					matchInfo.setYuCe(result);
					yuce = yuce + "\t" + result;
				} else if (winPercent == 0 || winPercent == 100 || drawPercent == 0 || drawPercent == 100
						|| lossPercent == 0 || lossPercent == 100) {
					yuce = "概率错误";
					result = "null";
				}else {
					 isShowYuCe = "暂无数据";
					 result = "null";
				}
				
				if ((winPercent + drawPercent + lossPercent) != 100) {
					yuce = "概率和不为100";
				}
				
			}
		}
		System.out.println(yuce);
		return result;
	}
	
	public static void setUniqueTournamentIds(){
		String[] a = uniqueTournamentIds.split(",");
		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
	}
	
	private static String URL = "https://smc.jdddata.com/api/scoredetail/getSsMatchAnalyzeInfoVerB?&mid=";
	public static String getYuCeData(String matchId) throws Exception{
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL+matchId);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
		} else {

		}
		String str = "";
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		int code = obj.getInt("code");
		// 判断预测接口是否正常
		if (code != 0) {
			return "接口无数据";
		} else {
			JSONObject jddBo = data.getJSONObject("jddBo");
			int innerCode = Integer.valueOf(jddBo.getString("innerCode"));
			if (innerCode != 1) {
				return "接口无预测";
			}

			int winPercent = Integer.valueOf(jddBo.getString("winPercent"));
			int drawPercent = Integer.valueOf(jddBo.getString("drawPercent"));
			int lossPercent = Integer.valueOf(jddBo.getString("lossPercent"));
			
			str ="概率：" + winPercent + "\t" + drawPercent + "\t" + lossPercent;
		}
		
		return str;
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
						checkYuceInfo(match[j]);
					}
				}
			}
		}
	}
}
