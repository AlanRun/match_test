package appData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smc.HeadTest;
import smc.JCSPTest;
import utils.HttpRequester;
import utils.HttpRespons;
import utils.LogWrite;

public class NewYuceTest {
	private static String url = "https://forecast.databiger.com/odds/forecast/getMatchForecastList?isFirstRequest=N&pts=1505980246355&pcode=android&version=1.0.0&platform=data&dateStr=";
	
	public static String getNewYuCeData(String issue) throws Exception{
		String str = "";
		String URL = url + issue;

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
		int code = obj.getInt("code");
		if (code !=1) {
			LogWrite.saveToFile("新预测数据为空");
		} else {
			JSONArray matchList = data.getJSONArray("matchList");
			for (int i = 0; i < matchList.size(); i++) {
				JSONObject match = (JSONObject) matchList.get(i);
				String matchId = match.getString("matchId");
				String homeTeamName = match.getString("homeTeamName");
				String awayTeamName = match.getString("awayTeamName");
				String matchTime = match.getString("matchTime");
				String uniName = match.getString("uniqueTournamentName");
				String winWdl = match.getString("winWdl");
				String drawWdl = match.getString("drawWdl");
				String lossWdl = match.getString("lossWdl");
				String winPercent = match.getString("winPercent");
				String drawPercent = match.getString("drawPercent");
				String lossPercent = match.getString("lossPercent");
				String recommendPercent = match.getString("recommendPercent");
				String winRecommend = match.getString("winRecommend");
				String drawRecommend = match.getString("drawRecommend");
				String lossRecommend = match.getString("lossRecommend");
				String matchResult = match.getString("matchResult");
				
				String info1 = matchId + "\t" + uniName + "\t" + homeTeamName + "\t" + awayTeamName + "\t" + matchTime;
				String info2 = HeadTest.getTeamName(matchId);
				if (!info1.equals(info2)) {
					LogWrite.saveToFile("对阵信息不匹配");
					LogWrite.saveToFile("new=[" + info1);
					LogWrite.saveToFile("old=[" + info2);
				} else {
					LogWrite.saveToFile(info1);
				}
				
				float hp = Float.valueOf(winPercent);
				float dp = Float.valueOf(drawPercent);
				float ap = Float.valueOf(lossPercent);
				
				float rp = 0;
				if (winRecommend.equals("1")) {
					LogWrite.saveToFile("推荐主胜\t");
					if (matchResult.equals("3")) {
						LogWrite.saveToFile("命中\t");
					}else {
						LogWrite.saveToFile("未中\t");
					}
					rp = rp + hp;
				}
				if (drawRecommend.equals("1")) {
					LogWrite.saveToFile("推荐平\t");
					if (matchResult.equals("1")) {
						LogWrite.saveToFile("命中\t");
					}else {
						LogWrite.saveToFile("未中\t");
					}
					rp = rp + dp;
				}
				if (lossRecommend.equals("1")) {
					LogWrite.saveToFile("推荐客胜\t");
					if (matchResult.equals("0")) {
						LogWrite.saveToFile("命中\t");
					}else {
						LogWrite.saveToFile("未中\t");
					}
					rp = rp + ap;
				}

				if (rp != Float.valueOf(recommendPercent)) {
					LogWrite.saveToFile("推荐概率有误");
					LogWrite.saveToFile("推荐概率：" + rp);
				} else {
					LogWrite.saveToFile("推荐概率：" + rp);
				}
				
				if ((hp + dp + ap) != 100) {
					LogWrite.saveToFile("预测概率和不为100");
					LogWrite.saveToFile(winPercent + "\t" + drawPercent + "\t" + lossPercent);
				}
				
				String odd1 =  winWdl + "\t" + drawWdl + "\t" + lossWdl;
				String odd2 = JCSPTest.getJCSp(matchId);
				if (!odd1.equals(odd2)) {
					LogWrite.saveToFile("对阵赔率不匹配");
					LogWrite.saveToFile("new=[" + odd1);
					LogWrite.saveToFile("old=[" + odd2);
				} else {
					LogWrite.saveToFile(odd1);
				}
				LogWrite.saveToFile("\n");
			}
		}
		return str;
	}
	
	
	public static void main(String[] args) throws Exception {
		getNewYuCeData("2017-09-17");
		getNewYuCeData("2017-09-18");
		getNewYuCeData("2017-09-19");
		getNewYuCeData("2017-09-20");
		getNewYuCeData("2017-09-21");
		getNewYuCeData("2017-09-22");
		getNewYuCeData("2017-09-23");
		getNewYuCeData("2017-09-24");
	}

}
