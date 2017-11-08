package smc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class TuiJian {
	static Date d = new Date();

	// 竞足头部接口
	private static String url_common = "https://smc.jdd.com/api/scoredetail/head?lotteryId=90&pcode=h5&pts=0&version=v2.1&matchid=";

	static ArrayList<String> list = new ArrayList<String>();

	public static void checkYuceInfo(String matchId) throws Exception {

		String URL = url_common + matchId;
		String yuce = "";

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return;
		} else {

		}

		try {
			JSONObject obj = JSONObject.fromObject(json);
			JSONObject data = obj.getJSONObject("data");
			String isShowYuCe = data.getString("isShowYuCe");
			String uniqueTournamentName = data.getString("uniqueTournamentName");
			String uniqueTournamentId = data.getString("uniqueTournamentId");

			// 判断赛事是否需要显示预测
			if (list.contains(uniqueTournamentId)) {
				if (isShowYuCe.equals("N")) {
					isShowYuCe = "赛事无预测";
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
						"https://smc.jdd.com/api/scoredetail/getSsMatchAnalyzeInfoVerB?lotteryId=90&pcode=h5&pts=0&version=v2.1&mid="
								+ matchId);
				json = hr.getContent();
				if (json == null || json.equals("")) {
					return;
				} else {

				}
				obj = JSONObject.fromObject(json);
				data = obj.getJSONObject("data");
				int code = obj.getInt("code");

				// 判断预测接口是否正常
				if (code != 0) {
					isShowYuCe = "预测异常";
				} else {
					JSONObject jddBo = data.getJSONObject("jddBo");
					int innerCode = Integer.valueOf(jddBo.getString("innerCode"));
					if (innerCode != 1) {
						isShowYuCe = "奖多多预测暂无数据";
					}
					// int winPercent=
					// Integer.valueOf(jddBo.getString("winPercent"));
					// int drawPercent=
					// Integer.valueOf(jddBo.getString("drawPercent"));
					// int lossPercent=
					// Integer.valueOf(jddBo.getString("lossPercent"));
					//
					// // 0 <=任意胜率 <= 90，则显示奖多多预测
					// if ((90>winPercent && winPercent>0) ||(90>drawPercent &&
					// drawPercent>0) || (90>lossPercent && lossPercent>0)) {
					//
					// } else if (winPercent==0 || winPercent==100 ||
					// drawPercent==0 || drawPercent==100 ||lossPercent==0 ||
					// lossPercent==100) {
					// isShowYuCe = "暂无数据";
					// } else {
					// isShowYuCe = "暂无数据";
					// }

					JSONObject poissonBo = data.getJSONObject("poissonBo");
					innerCode = Integer.valueOf(poissonBo.getInt("innerCode"));

					// 判断泊松数据是否正常
					if (innerCode != 0) {

						int score1Percent = Integer.valueOf(poissonBo.getString("score1Percent"));
						int score2Percent = Integer.valueOf(poissonBo.getString("score2Percent"));
						int score3Percent = Integer.valueOf(poissonBo.getString("score3Percent"));

						if ((score1Percent + score2Percent + score3Percent) == 0) {
							isShowYuCe = "泊松数据异常";
						}

						request = new HttpRequester();
						request.setDefaultContentEncoding("UTF-8");

						// 泊松详情接口
						hr = request.sendGet(
								"https://smc.jdd.com/api/scoredetail/getSsMatchAnalyzePoissonInfoVerB?lotteryId=90&pcode=h5&pts=0&version=v2.1&mid="
										+ matchId);
						json = hr.getContent();
						if (json == null || json.equals("")) {
							return;
						} else {

						}
						obj = JSONObject.fromObject(json);
						data = obj.getJSONObject("data");
						code = obj.getInt("code");

						// 判断泊松详情是否正常
						if (code != 0) {
							isShowYuCe = "泊松详情异常";
						}
					} else {
						yuce = "泊松无数据";
					}

				}
			}
			System.out.println(matchId + "," + uniqueTournamentName + "," + isShowYuCe + "," + yuce);
		} catch (Exception e) {
			return;
		}
	}

	public static void getTuiJianMatchs(String issue) throws IOException {
		String url = "https://smc.jdd.com/api/scoredetail/getMatchCommend?pcode=h5&version=v2.1&dateStr=";
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(url + issue);
		String json = hr.getContent();
		String isShowYuCe = "";
		int p = -1;
		try {
			JSONObject obj = JSONObject.fromObject(json);
			JSONObject data = obj.getJSONObject("data");
			JSONArray matchList = data.getJSONArray("matchList");
			for (int i = 0; i < matchList.size(); i++) {
				JSONObject match = (JSONObject) matchList.get(i);
				String matchId = match.getString("matchId");
				String jzNum = match.getString("jzNum");
				String yeCeWdl = match.getString("yeCeWdl");
				String yuCePercent = match.getString("yuCePercent");
				String backOddsValue = match.getString("backOddsValue");

				System.out.println(matchId + "\t" + jzNum + "\t" + yuCePercent + "\t" + backOddsValue);
				
				hr = request.sendGet(
						"https://smc.jdd.com/api/scoredetail/getSsMatchAnalyzeInfoVerB?lotteryId=90&pcode=h5&pts=0&version=v2.1&mid="
								+ matchId);
				json = hr.getContent();
				if (json == null || json.equals("")) {
					return;
				} else {

				}
				obj = JSONObject.fromObject(json);
				data = obj.getJSONObject("data");
				int code = obj.getInt("code");

				// 判断预测接口是否正常
				if (code != 0) {
					isShowYuCe = "预测异常";
				} else {
					JSONObject jddBo = data.getJSONObject("jddBo");
					int innerCode = Integer.valueOf(jddBo.getString("innerCode"));
					if (innerCode != 1) {
						isShowYuCe = "奖多多预测暂无数据";
					}
					int winPercent = Integer.valueOf(jddBo.getString("winPercent"));
					int drawPercent = Integer.valueOf(jddBo.getString("drawPercent"));
					int lossPercent = Integer.valueOf(jddBo.getString("lossPercent"));

					
					if (yeCeWdl.equals("3")) {
						p  = winPercent;
						isShowYuCe = "主胜";
					} else if (yeCeWdl.equals("1")) {
						p = drawPercent;
						isShowYuCe = "平";
					} else {
						p = lossPercent;
						isShowYuCe = "客胜";
					}

				}
				System.out.println(matchId + "\t" + jzNum + "\t" + p + "\t" + isShowYuCe);
			}

		} catch (Exception e) {
			return;
		}

	}

	public static void main(String[] args) throws Exception {
		
		getTuiJianMatchs("2017-08-17");
		
		
//		// 需要显示预测的赛事ID
//		String uniqueTournamentIds = "17,242,131,8,37,480,498,11,18,384,34,35,36,136,182,373,23,155,335,851,1598,323,20,325,308,463,217,203,238,244,402,679,44,7,352,333,38,24,196";
//
//		String[] a = uniqueTournamentIds.split(",");
//		for (int i = 0; i < a.length; i++) {
//			list.add(a[i]);
//		}
//
//		// 获取期次
//		String issues = IssueTest.getJZIssues();
//		if (!issues.equals("")) {
//			String[] issue = issues.split(",");
//			for (int i = 0; i < issue.length; i++) {
//				// 获取对阵
//				String matchs = MatchsTest.getJZMatchs(issue[i]);
//				if (!matchs.equals("")) {
//					String[] match = matchs.split(",");
//					for (int j = 0; j < match.length; j++) {
//						checkYuceInfo(match[j]);
//					}
//				}
//			}
//		}
	}
}
