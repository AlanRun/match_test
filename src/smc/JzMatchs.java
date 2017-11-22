package smc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helper.DataUrls;
import helper.FootballMatch;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class JzMatchs {

	private static String jz_url = DataUrls.ft_qlive;

	static Map<String, String> params = new HashMap<String, String>();

	public static String getJZMatchs(String issue) throws IOException {

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(jz_url + issue);

		String matchs = "";

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return matchs;
		} else {

		}
		
		try {
			JSONObject obj = JSONObject.fromObject(json);
			JSONArray MIarray = obj.getJSONArray("MI");

			for (int i = 0; i < MIarray.size(); i++) {
				JSONObject MI = MIarray.getJSONObject(i);
				JSONArray MSarray = MI.getJSONArray("MS");

				for (int j = 0; j < MSarray.size(); j++) {
					JSONObject M = MSarray.getJSONObject(j);
					int match = M.getInt("MId");
					matchs = matchs + match;
					if (j < MSarray.size() - 1) {
						matchs = matchs + ",";
					}
				}
			}
		} catch (Exception e) {
		}

		return matchs;
	}
	
	/**
	 * 获取指定期次dcds比分列表接口期次对阵
	 * @param issue
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<FootballMatch> getMatchList(String issue) throws IOException {
		ArrayList<FootballMatch> list = new ArrayList<FootballMatch>();
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(jz_url + issue);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return list;
		} else {
			System.out.println(json);
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray MIarray = obj.getJSONArray("MI");

		for (int i = 0; i < MIarray.size(); i++) {
			JSONObject MI = MIarray.getJSONObject(i);
			JSONArray MSarray = MI.getJSONArray("MS");

			for (int j = 0; j < MSarray.size(); j++) {
				JSONObject M = MSarray.getJSONObject(j);

				FootballMatch m = new FootballMatch();

				m.setI(M.getString("I"));
				m.setLId(M.getInt("LId"));
				m.setNUM(M.getString("NUM"));
				m.setMId("" + M.getInt("MId"));
				m.setTABS(M.getString("TABS"));
				m.setSMD(M.getString("SMD") + ":00");
				m.setSC(M.getInt("SC"));
				
				
				//(-2)其他，无对应转码；
				//(-1)无直播；
				//(0)未开始；
				//(1)完成；
				//(2)进行中

				int IC = M.getInt("IC");
				if (IC==2) {
					IC = 1;
				} else if (IC == 1) {
					IC = 2;
				} 
				
				m.setIC(IC);
				
				if (IC == 0) {
					m.setScoreHalf("");
					m.setScoreNormal("");
				} else {
					m.setScoreHalf("" + M.getInt("HHS") + ":" + M.getInt("AHS"));
					m.setScoreNormal("" + M.getInt("HS") + ":" + M.getInt("AS"));
				}
				
				String SS = M.getString("SS");
				if (SS.equals("已结束")) {
					SS = "完场";
				}
				
				m.setSS(SS);
				m.setHT(M.getString("HT"));
				
				m.setATD(M.getInt("ATD"));
				m.setHTD(M.getInt("HTD"));
				
				m.setAT(M.getString("AT"));
				m.setHS(M.getInt("HS"));
				m.setAS(M.getInt("AS"));
				m.setHHS(M.getInt("HHS"));
				m.setAHS(M.getInt("AHS"));
//				m.setIR(M.getBoolean("IR"));

//				System.out.println(m.toString());
				list.add(m);
			}
		}
		return list;
	}
	
	public static String getAllJZMatchs() throws IOException{
		String result = "";
		String issues = JzIssue.getJZIssues();
		if (!issues.equals("")) {
			String[] issue = issues.split(",");
			for (int i = 0; i < issue.length; i++) {
				// 获取对阵
				String matchs = JzMatchs.getJZMatchs(issue[i]);
				if (!matchs.equals("")) {
					result = result + matchs + ",";
				}
			}
		}
		result = result.substring(0, result.length() -1);
		return result;
	}

	public static void main(String[] args) throws IOException {
		getMatchList("2017-11-21");
//		getAllJZMatchs();
//		String matchs = getJZMatchs("2017-10-13");
//		System.out.println(matchs);
//		String[] list =  matchs.split(",");
//		for (int i = 0; i < list.length; i++) {
//			new TeamRank();
//			TeamRank.getTeamRank(list[i]);
//		}
	}

}
