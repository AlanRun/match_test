package appData;


import helper.AppReq;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smc.BiFaData;
import smc.HeadTest;
import smc.JCSPTest;
import utils.LogWrite;

public class NewBiFaTest {

	static String url = "http://smc.jdddata.com/api/soccer/matchlist/spdex";
	static String params = "{\"header\":{\"appVersion\":\"1.0.0\",\"cmdID\":\"0\",\"platformVersion\":\"6.0\",\"idfa\":\"\",\"action\":\"\",\"imei\":\"861759032966847\",\"cmdName\":\"app_zz\",\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"platformCode\":\"Android\",\"phoneName\":\"HUAWEI\"},\"body\":\"{\\\"body\\\":{}}\"}";

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
//			 System.out.println(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject match = (JSONObject) data.get(i);
			int innerCode = match.getInt("innerCode");
			
			if (innerCode != 1) {
				continue;
			}
			
			JSONObject matchInfo = match.getJSONObject("matchInfo");
			String touName = matchInfo.getString("tournamentName");
			String homeTeamName = matchInfo.getString("homeTeamName");
			String awayTeamName = matchInfo.getString("awayTeamName");
			String matchTime = matchInfo.getString("matchTime");
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
			
			String htp = match.getString("homeTotalPercent");
			String dtp = match.getString("drawTotalPercent");
			String atp = match.getString("awayTotalPercent");
			String hlp = match.getString("homeLargePercent");
			String dlp = match.getString("drawLargePercent");
			String alp = match.getString("awayLargePercent");
			String allTp = "\t" + "成交量：" + htp + "\t" + dtp + "\t" + atp;
			String alllp = "\t" + "大额交易：" + hlp + "\t" + dlp + "\t" + alp;
			
			if (Math.abs(Float.valueOf(htp) + Float.valueOf(dtp) + Double.valueOf(atp)-1) == 0) {
				System.err.println("必发成交量和不为1");
				System.err.println(allTp);
			} else {
				System.out.println(allTp);
			}
			
			if ((Double.valueOf(hlp) + Double.valueOf(dlp) + Double.valueOf(alp)) != 1) {
				System.err.println("必发大额交易和不为1");
				System.err.println(alllp);
			} else {
				System.out.println(alllp);
			}
			
			String homeSpdex = match.getString("homeSpdex");
			String drawSpdex = match.getString("drawSpdex");
			String awaySpdex = match.getString("awaySpdex");
			
			String price1 = "\t" + "必发价位：" + homeSpdex + "\t" + drawSpdex + "\t" + awaySpdex;
			String price2 = BiFaData.getBiFaPrice(matchId);
			if (!price1.equals(price2)) {
				System.err.println("必发价位不匹配");
				System.out.println("new=[" + price1);
				System.out.println("old=[" + price2);
			} else {
				System.out.println(price1);
			}
			
			String homeAverage = match.getString("homeAverage");
			String drawAverage = match.getString("drawAverage");
			String awayAverage = match.getString("awayAverage");
			String ave1 = "\t" + "百家平均：" + homeAverage + "\t" + drawAverage + "\t" + awayAverage;
			String ave2 = JCSPTest.getAveSp(matchId);
			if (!ave1.equals(ave2)) {
				System.err.println("百家平均不匹配");
				System.out.println("new=[" + ave1);
				System.out.println("old=[" + ave2);
			} else {
				System.out.println(price1);
			}
			System.out.println("old=["+BiFaData.getBiFaAmount(matchId));
			System.out.println("old=["+BiFaData.getBiFaBig(matchId));
		}
	}

	public static void main(String[] args) throws Exception {
		getTbigerData();
	}

}
