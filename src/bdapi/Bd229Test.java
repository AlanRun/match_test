package bdapi;

import java.util.ArrayList;
import org.dom4j.DocumentException;
import helper.AppReq;
import helper.DataUrls;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.LogWrite;

public class Bd229Test {

	public static String jdd_jz_url = DataUrls.url_bd;
	public static String bd_params = DataUrls.params_229;

	/**
	 * 获取jdd北单投注对阵 action=229
	 * 
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JzMatchSpInfo> getTeadRankAndHis() throws Exception {
		ArrayList<JzMatchSpInfo> list = new ArrayList<JzMatchSpInfo>();

		String json = AppReq.getResStr(jdd_jz_url, bd_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
//			 LogWrite.saveToFile(json);
			System.out.println(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");

		for (int i = 0; i < data.size(); i++) {
			JSONObject wk = (JSONObject) data.get(i);

			JSONArray matchList = wk.getJSONArray("matchList");
			for (int j = 0; j < matchList.size(); j++) {
				JSONObject matchs = (JSONObject) matchList.get(j);
				JSONArray matchlist = matchs.getJSONArray("matchlist");
				for (int k = 0; k < matchlist.size(); k++) {
					JzMatchSpInfo jzMatch = new JzMatchSpInfo();
					JSONObject match = (JSONObject) matchlist.get(k);
					String IssueNo = match.getString("IssueNo");
					String NMm = match.getString("NMm");
					String HTeam = match.getString("HTeam");
					String VTeam = match.getString("VTeam");
//					String HomeFieldRank = match.getString("HomeFieldRank");
//					String AwayFieldRank = match.getString("AwayFieldRank");
					String HomeRank = match.getString("HomeRank");
					String AwayRank = match.getString("AwayRank");
					String HAHistory = match.getString("HAHistory");
					String HomeHistory = match.getString("HomeHistory");
					String AwayHistory = match.getString("AwayHistory");
					String AvgSP = match.getString("AvgSP");

					jzMatch.setNMm(NMm);
					jzMatch.setHTeam(HTeam);
					jzMatch.setVTeam(VTeam);
//					jzMatch.setHomeFieldRank(HomeFieldRank);
//					jzMatch.setAwayFieldRank(AwayFieldRank);
					jzMatch.setHomeRank(HomeRank);
					jzMatch.setAwayRank(AwayRank);
					jzMatch.setHAHistory(HAHistory);
					jzMatch.setHomeHistory(HomeHistory);
					jzMatch.setAwayHistory(AwayHistory);
					jzMatch.setAvgSP(AvgSP);
					
					System.out.println(IssueNo + "  " + HTeam + "\t" + VTeam);
//					System.out.println("球队实力\t" + HomeFieldRank + "\t" + AwayFieldRank);
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
//					System.out.println("平均欧赔\t" + AvgSP);
					
					LogWrite.saveToFile(IssueNo + "  " + HTeam + "\t" + VTeam);
//					LogWrite.saveToFile("球队实力\t" + HomeFieldRank + "\t" + AwayFieldRank);
					LogWrite.saveToFile("球队排名\t" + HomeRank + "\t" + AwayRank);
					LogWrite.saveToFile("两队交锋\t" + HAHistory);
					LogWrite.saveToFile("近期状态\t" + HomeHistory + "\t" + AwayHistory);
//					LogWrite.saveToFile("平均欧赔\t" + AvgSP);
					System.out.println();
					
					list.add(jzMatch);
				}
			}
		}
		return list;
	}

	public static String checkSp(String value) {
		if (!value.contains(".")) {
			value = value + ".00";
		}

		// 补齐小数点后两位
		if (!value.substring(value.length() - 3, value.length() - 2).equals(".")) {
			value = value + "0";
		}
		return value;
	}

	public static void main(String[] args) throws Exception {
		getTeadRankAndHis();
	}

}