package bdapi;

import java.io.IOException;
import java.util.ArrayList;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.JlMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.LogWrite;

public class Jl2031Test {
	static String gf_jl_url = "http://info.sporttery.cn/interface/interface_mixed.php?action=bk_list&_=";
	static String jdd_jl_url = DataUrls.url_bd;
	static String jl_params = DataUrls.params_2031;

	/**
	 * 获取jdd竞篮投注对阵赔率 action=2031
	 * 
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	public static ArrayList<JlMatchSpInfo> getTeadRankAndHis() throws IOException, AesException {
		ArrayList<JlMatchSpInfo> list = new ArrayList<JlMatchSpInfo>();
		
		String json = AppReq.getResStr(jdd_jl_url, jl_params);
		if (json == null || json.equals("")) {
			LogWrite.saveToFile("empty content!!!");
		} else {
			// LogWrite.saveToFile(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject wk = (JSONObject) data.get(i);
			JSONArray Matches = wk.getJSONArray("Matches");
			for (int j = 0; j < Matches.size(); j++) {
				JlMatchSpInfo jlMatch = new JlMatchSpInfo();
				JSONObject match = (JSONObject) Matches.get(j);

				String MID = match.getString("MID");
				String HTeam = match.getString("HTeam");
				String VTeam = match.getString("VTeam");
				
				String HomeRank = match.getString("HomeRank");
				String AwayRank = match.getString("AwayRank");
				
				String HAHistory = match.getString("HAHistory");
				String HomeHistory = match.getString("HomeHistory");
				String AwayHistory = match.getString("AwayHistory");
				
				String HomeAlias = match.getString("HomeAlias");
				String AwayAlias = match.getString("Awaylias");
				
				String HomeRFHistory = match.getString("HomeRFHistory");
				String HomeDXFHistory = match.getString("HomeDXFHistory");
				String AwayRFHistory = match.getString("AwayRFHistory");
				String AwayDXFHistory = match.getString("AwayDXFHistory");
				
				System.out.println(MID + "  " + VTeam + "\t" + HTeam);
				System.out.println("让分的战绩\t" + AwayRFHistory + "\t" + HomeRFHistory);
				System.out.println("大小分战绩\t" + AwayDXFHistory + "\t" + HomeDXFHistory);
				System.out.println("球队排名\t" + AwayAlias + " " + AwayRank + "\t" + HomeAlias + " " + HomeRank);
				System.out.println("两队交锋\t" + HAHistory);
				System.out.println("近期状态\t" + AwayHistory + "\t" + HomeHistory);
				
				LogWrite.saveToFile(MID + "  " + VTeam + "\t" + HTeam);
				LogWrite.saveToFile("让分的战绩\t" + AwayRFHistory + "\t" + HomeRFHistory);
				LogWrite.saveToFile("大小分战绩\t" + AwayDXFHistory + "\t" + HomeDXFHistory);
				LogWrite.saveToFile("球队排名\t" + AwayAlias + " " + AwayRank + "\t" + HomeAlias + " " + HomeRank);
				LogWrite.saveToFile("两队交锋\t" + HAHistory);
				LogWrite.saveToFile("近期状态\t" + AwayHistory + "\t" + HomeHistory);
				System.out.println();

				list.add(jlMatch);
			}
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		getTeadRankAndHis();
	}
}
