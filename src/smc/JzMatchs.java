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
				m.setMId(M.getInt("MId"));
				m.setTABS(M.getString("TABS"));
				m.setSMD(M.getString("SMD"));
				m.setSC(M.getInt("SC"));
				m.setIC(M.getInt("IC"));
				m.setSS(M.getString("SS"));
				m.setHT(M.getString("HT"));
				m.setAT(M.getString("AT"));
				m.setHS(M.getInt("HS"));
				m.setAS(M.getInt("AS"));
				m.setHHS(M.getInt("HHS"));
				m.setAHS(M.getInt("AHS"));
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
		getAllJZMatchs();
//		String matchs = getJZMatchs("2017-10-13");
//		System.out.println(matchs);
//		String[] list =  matchs.split(",");
//		for (int i = 0; i < list.length; i++) {
//			new TeamRank();
//			TeamRank.getTeamRank(list[i]);
//		}
	}

}
