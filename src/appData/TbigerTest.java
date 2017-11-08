package appData;

import helper.AppReq;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smc.BiFaData;
import smc.HeadTest;
import smc.JCSPTest;
import smc.YuCeTest;
import utils.LogWrite;

public class TbigerTest {

	static String url = "http://smc.jdddata.com/api/soccer/home/tbiger";
	static String params = "{\"header\":{\"appVersion\":\"1.0.0\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"idfa\":\"\",\"action\":\"\",\"imei\":\"861759032966847\",\"cmdName\":\"app_zz\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\"},\"body\":\"\"}";

	/**
	 * 获取jdd竞足投注对阵赔率 action=202
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void getTbigerData() throws Exception {
		String json = AppReq.getResStr(url, params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			// System.out.println(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject module = (JSONObject) data.get(i);
			String moduleId = module.getString("moduleId");
			String moduleName = module.getString("moduleName");
			if (moduleId.equals("3")) {
				System.out.println(moduleName);
				JSONObject content = module.getJSONObject("content");
				String maxLoss = content.getString("maxLoss");
				String maxPercent = content.getString("maxPercent");
				String maxSide = content.getString("maxSide");

				float per = Float.valueOf(maxPercent);
				maxPercent = "" + per * 100 + "%";

				JSONObject matchInfo = content.getJSONObject("matchInfo");
				String homeTeamName = matchInfo.getString("homeTeamName");
				String awayTeamName = matchInfo.getString("awayTeamName");
				String matchTime = matchInfo.getString("matchTime");
				String touName = matchInfo.getString("tournamentName");
				String matchId = matchInfo.getString("matchId");

				String info1 = matchId + "\t" + touName + "\t" + homeTeamName + "\t" + awayTeamName + "\t" + matchTime;
				String info2 = HeadTest.getTeamName(matchId);
				if (!info1.equals(info2)) {
					System.err.println("对阵信息不匹配");
					System.err.println("new=[" + info1);
					System.err.println("old=[" + info2);
				} else {
					System.out.println(info1);
				}

				String bifa1 = "";
				if (maxSide.equals("3")) {
					bifa1 = "主队必发交易占比极大，如果取胜，则庄家盈亏" + maxLoss + "\t 主队交易占比：" + maxPercent;
				} else if (maxSide.equals("1")) {
					bifa1 = "平必发交易占比极大，如果取胜，则庄家盈亏" + maxLoss + "\t 平交易占比：" + maxPercent;
				} else if (maxSide.equals("0")) {
					bifa1 = "客队必发交易占比极大，如果取胜，则庄家盈亏" + maxLoss + "\t 客队交易占比：" + maxPercent;
				} else {
					bifa1 = "接口返回比分大热数据错误";
					System.err.println("maxSide=" + maxSide);
				}

				String bifa2 = BiFaData.getBiFaData(matchId);
				if (!bifa1.equals(bifa2)) {
					System.err.println("对阵必发不匹配");
					System.err.println("new=[" + bifa1);
					System.err.println("old=[" + bifa2);
				} else {
					System.out.println(bifa1);
				}
				System.out.println();
			} else if (moduleId.equals("4")) {
				System.out.println(moduleName);
				JSONObject content = module.getJSONObject("content");
				String homeIndex = content.getString("homeIndex");
				String drawIndex = content.getString("drawIndex");
				String awayIndex = content.getString("awayIndex");

				JSONObject matchInfo = content.getJSONObject("matchInfo");
				String homeTeamName = matchInfo.getString("homeTeamName");
				String awayTeamName = matchInfo.getString("awayTeamName");
				String matchTime = matchInfo.getString("matchTime");
				String touName = matchInfo.getString("tournamentName");
				String matchId = matchInfo.getString("matchId");
				
				String info1 = matchId + "\t" + touName + "\t" + homeTeamName + "\t" + awayTeamName + "\t" + matchTime;
				String info2 = HeadTest.getTeamName(matchId);
				if (!info1.equals(info2)) {
					System.err.println("对阵信息不匹配");
					System.err.println("new=[" + info1);
					System.err.println("old=[" + info2);
				} else {
					System.out.println(info1);
				}
				
				String hotCool1 = "\t" + "冷热指数：" + "主胜 " + homeIndex + "\t" + "平 " + drawIndex + "\t" + "客胜 " + awayIndex;
				String hotCool2 = BiFaData.getHotCoolData(matchId);
				if (!hotCool1.equals(hotCool2)) {
					System.err.println("对阵冷热不匹配");
					System.err.println("new=[" + hotCool1);
					System.err.println("old=[" + hotCool2);
				} else {
					System.out.println(hotCool1);
				}
				System.out.println();
			} else if (moduleId.equals("5")) {
				System.out.println(moduleName);
				JSONArray content = module.getJSONArray("content");
				for (int j = 0; j < content.size(); j++) {
					JSONObject match = (JSONObject) content.get(j);
					String homeOdds = match.getString("homeOdds");
					String drawOdds = match.getString("drawOdds");
					String awayOdds = match.getString("awayOdds");
					String homePercent = match.getString("homePercent");
					String drawPercent = match.getString("drawPercent");
					String awayPercent = match.getString("awayPercent");
					
					JSONObject matchInfo = match.getJSONObject("matchInfo");
					String homeTeamName = matchInfo.getString("homeTeamName");
					String awayTeamName = matchInfo.getString("awayTeamName");
					String matchTime = matchInfo.getString("matchTime");
					String touName = matchInfo.getString("tournamentName");
					String matchId = matchInfo.getString("matchId");
					
					String info1 = matchId + "\t" + touName + "\t" + homeTeamName + "\t" + awayTeamName + "\t" + matchTime;
					String info2 = HeadTest.getTeamName(matchId);
					if (!info1.equals(info2)) {
						System.err.println("对阵信息不匹配");
						System.err.println("new=[" + info1);
						System.err.println("old=[" + info2);
					} else {
						System.out.println(info1);
					}
					
					float hp = Float.valueOf(homePercent);
					float dp = Float.valueOf(drawPercent);
					float ap = Float.valueOf(awayPercent);
					
					if ((hp + dp + ap) != 1.0) {
						System.err.println("预测概率和不为1");
						System.err.println(homePercent + "\t" + drawPercent + "\t" + awayPercent);
					}
					
					homePercent = homePercent.substring(2, homePercent.length());
					drawPercent = drawPercent.substring(2, drawPercent.length());
					awayPercent = awayPercent.substring(2, awayPercent.length());
					
					System.err.println(hp*100);
					
					String odd1 =  homeOdds + "\t" + drawOdds + "\t" + awayOdds;
					String odd2 = JCSPTest.getJCSp(matchId);
					if (!odd1.equals(odd2)) {
						System.err.println("对阵赔率不匹配");
						System.err.println("new=[" + odd1);
						System.err.println("old=[" + odd2);
					} else {
						System.out.println(odd1);
					}
					String yuce1 = "概率：" + homePercent + "\t" + drawPercent + "\t" + awayPercent;
					String yuce2 = YuCeTest.getYuCeData(matchId);
					if (!yuce1.equals(yuce2)) {
						System.err.println("对阵预测不匹配");
						System.err.println("new=[" + yuce1);
						System.err.println("old=[" + yuce2);
					} else {
						System.out.println(yuce1);
					}
					
					System.out.println();
				}
				
			}
			
		}
	}

	public static void main(String[] args) throws Exception {
		getTbigerData();
	}

}
