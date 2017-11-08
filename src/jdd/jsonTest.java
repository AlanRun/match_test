package jdd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helper.FootballMatch;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class jsonTest {

	private static String URL2 = "http://dcds.jdd.com/Api.Score.New/Ajax/Live.ashx";

	public static void testMyMatchs() throws IOException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "qlive");
		 params.put("issue", "2016-09-21");
		 params.put("playid", "9001");
		params.put("lotteryid", "90");
		params.put("pts", "0");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(URL2, params);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.err.println("empty content!!!");
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray MIarray = obj.getJSONArray("MI");
		
		ArrayList<FootballMatch> list = new ArrayList<FootballMatch>();
		
		for (int i = 0; i < MIarray.size(); i++) {
			JSONObject MI = MIarray.getJSONObject(i);
			JSONArray MSarray = MI.getJSONArray("MS");

			for (int j = 0; j < MSarray.size(); j++) {
				JSONObject M = MSarray.getJSONObject(j);
				
				FootballMatch m = new FootballMatch();
				
//				Object I = M.get("I");
//				m.setI(I);
//
//				Object LId = M.get("LId");
//				m.setLId(LId);
//
//				Object NUM = M.get("NUM");
//				m.setNUM(NUM);
//
//				Object MId = M.get("MId");
//				m.setMId(MId);
//
//				Object TABS = M.get("TABS");
//				m.setTABS(TABS);
//
//				Object SMD = M.get("SMD");
//				m.setSMD(SMD);
//
//				Object SC = M.get("SC");
//				m.setSC(SC);
//
//				Object IC = M.get("IC");
//				m.setIC(IC);
//
//				Object SS = M.get("SS");
//				m.setSS(SS);
//
//				Object HT = M.get("HT");
//				m.setHT(HT);
//
//				Object AT = M.get("AT");
//				m.setAT(AT);
//
//				Object HR = M.get("HR");
//				m.setHR(HR);
//
//				Object HY = M.get("HY");
//				m.setHY(HY);
//
//				Object HS = M.get("HS");
//				m.setHS(HS);
//
//				Object AR = M.get("AR");
//				m.setAR(AR);
//
//				Object AY = M.get("AY");
//				m.setAY(AY);
//
//				Object AS = M.get("AS");
//				m.setAS(AS);
//				
//				Object HHS = M.get("HHS");
//				m.setHHS(HHS);
//				
//				Object AHS = M.get("AS");
//				m.setAHS(AHS);
				
				list.add(m);
				System.out.println(m.toString());
			}
		}

	}

	public static void main(String[] args) throws IOException {

		testMyMatchs();
	}

}
