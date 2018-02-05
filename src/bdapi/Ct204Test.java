package bdapi;

import java.util.ArrayList;
import helper.AppReq;
import helper.DataUrls;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.LogWrite;

public class Ct204Test {

	static String jdd_jz_url = DataUrls.url_bd;
	static String jz_params = DataUrls.params_204;

	/**
	 * 获取jdd竞足投注对阵赔率 action=202
	 * 
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JzMatchSpInfo> getTeadRankAndHis() throws Exception {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jz_url, jz_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			 LogWrite.saveToFile(json);
		}

		if (json.contains("无期次数据")) {
			return list;
		}
		
		JSONObject obj = JSONObject.fromObject(json);

		JSONObject data = obj.getJSONObject("data");
		JSONArray Matches = data.getJSONArray("Matches");
		for (int j = 0; j < Matches.size(); j++) {
			JzMatchSpInfo jzMatch = new JzMatchSpInfo();
			JSONObject match = (JSONObject) Matches.get(j);

			String No = match.getString("No");
			String HTeam = match.getString("HTeam");
			String VTeam = match.getString("VTeam");
//			String HomeFieldRank = match.getString("HomeFieldRank");
//			String AwayFieldRank = match.getString("AwayFieldRank");
			String HomeRank = match.getString("HomeRank");
			String AwayRank = match.getString("AwayRank");
			String HAHistory = match.getString("HAHistory");
			String HomeHistory = match.getString("HomeHistory");
			String AwayHistory = match.getString("AwayHistory");
			String OuOdds = match.getString("OuOdds");

//			jzMatch.setMID(No);
//			jzMatch.setNMm(NMm);
//			jzMatch.setHTeam(HTeam);
//			jzMatch.setVTeam(VTeam);
//			jzMatch.setHomeFieldRank(HomeFieldRank);
//			jzMatch.setAwayFieldRank(AwayFieldRank);
//			jzMatch.setHomeRank(HomeRank);
//			jzMatch.setAwayRank(AwayRank);
//			jzMatch.setHAHistory(HAHistory);
//			jzMatch.setHomeHistory(HomeHistory);
//			jzMatch.setAwayHistory(AwayHistory);
//			jzMatch.setAvgSP(AvgSP);
			
			System.out.println(No + "  " + HTeam + "\t" + VTeam);
//			System.out.println("球队实力\t" + HomeFieldRank + "\t" + AwayFieldRank);
			System.out.println("平均欧赔\t" + OuOdds);
			System.out.println("球队排名\t" + HomeRank + "\t" + AwayRank);
			if (HAHistory.equals("") || HAHistory.equals("null")) {
				System.err.println("两队交锋\t 无数据");
			} else {
				System.out.println("两队交锋\t" + HAHistory);
			}
			if (HomeHistory.equals("") || HomeHistory.equals("null")) {
				System.err.println("近期状态\t 无数据");
			} else {
				System.out.println("近期状态\t" + HomeHistory + "\t" + AwayHistory);
			}
			
			LogWrite.saveToFile(No + "  " + HTeam + "\t" + VTeam);
//			LogWrite.saveToFile("球队实力\t" + HomeFieldRank + "\t" + AwayFieldRank);
			LogWrite.saveToFile("平均欧赔\t" + OuOdds);
			LogWrite.saveToFile("球队排名\t" + HomeRank + "\t" + AwayRank);
			LogWrite.saveToFile("两队交锋\t" + HAHistory);
			LogWrite.saveToFile("近期状态\t" + HomeHistory + "\t" + AwayHistory);
			System.out.println();

			list.add(jzMatch);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		getTeadRankAndHis();
	}

}
