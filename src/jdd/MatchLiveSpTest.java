package jdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import helper.AsianMarker;
import helper.EuropMarker;
import helper.FootballMatch;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.GetConnectionSqlServer;
import utils.HttpRequester;
import utils.HttpRespons;

public class MatchLiveSpTest {

	private static String URL = "http://saishi.dc.jdd.com/Ajax/Soccer.ashx";
	private static String URL2 = "http://dcds.jdd.com/Api.Score.New/Ajax/Live.ashx";

	static Map<String, String> params = new HashMap<String, String>();
	static Map<String, String> params1 = new HashMap<String, String>();
	static Map<String, String> params2 = new HashMap<String, String>();

	public static ArrayList<FootballMatch> getMatchs(String issue) throws IOException {
		ArrayList<FootballMatch> list = new ArrayList<FootballMatch>();
		params2.put("action", "qlive");
		params2.put("issue", issue);
		params2.put("playid", "9001");
		params2.put("lotteryid", "90");
		params2.put("pts", "0");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(URL2, params2);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return list;
		} else {
//			System.out.println(json);
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
				System.out.print(M.get("MId")+",");
				list.add(m);
			}
		}
		return list;
	}

	public static ArrayList<AsianMarker> getAsianOdds(String mid) throws Exception {

		ArrayList<AsianMarker> list = new ArrayList<AsianMarker>();
		params.put("cmsm", "asianOddsCallback");
		params.put("action", "asianodds");
		params.put("matchid", mid);
		params.put("pcode", "h5");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(URL, params);

		String json = (hr.getContent().split("\\(")[1]).split("\\)")[0];

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return list;
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray DataArray = obj.getJSONArray("Data");

		for (int i = 0; i < DataArray.size(); i++) {
			JSONObject Data = DataArray.getJSONObject(i);
			AsianMarker marker = new AsianMarker();
			int BookMarkerId = Data.getInt("BookMarkerId");
			marker.setBookMarkerId(BookMarkerId);
			
			String BookMarkerName = Data.getString("BookMarkerName");
			marker.setBookMarkerName(BookMarkerName);
			
			double Hcap = Data.getDouble("Hcap");
			marker.setHcap(Hcap);
			
			double FirstHome = Data.getDouble("FirstHome");
			marker.setFirstHome(FirstHome);
			
			double FirstAway = Data.getDouble("FirstAway");
			marker.setFirstAway(FirstAway);
			
			double LastHcap = Data.getDouble("LastHcap");
			marker.setLastHcap(LastHcap);
			
			double LastHome = Data.getDouble("LastHome");
			marker.setLastHome(LastHome);
			
			double LastAway = Data.getDouble("LastAway");
			marker.setLastAway(LastAway);
			
			int LastHomeChange = Data.getInt("LastHomeChange");
			marker.setLastHomeChange(LastHomeChange);
			
			int LastAwayChange = Data.getInt("LastAwayChange");
			marker.setLastAwayChange(LastAwayChange);
			
			list.add(marker);
		}
		return list;
	}

	public static ArrayList<EuropMarker> getEuropOdds(String mid) throws Exception {

		ArrayList<EuropMarker> list = new ArrayList<EuropMarker>();
		params.put("cmsm", "europOddsCallback");
		params.put("action", "europodds");
		params.put("matchid", mid);
		params.put("pcode", "h5");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(URL, params);

		String json = (hr.getContent().split("\\(")[1]).split("\\)")[0];

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return list;
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray DataArray = obj.getJSONArray("Data");

		for (int i = 0; i < DataArray.size(); i++) {
			JSONObject Data = DataArray.getJSONObject(i);
			EuropMarker marker = new EuropMarker();

			int BookMarkerId = Data.getInt("BookMarkerId");
			marker.setBookMarkerId(BookMarkerId);
			
			String BookMarkerName = Data.getString("BookMarkerName");
			marker.setBookMarkerName(BookMarkerName);
			
			double FirstWin = Data.getDouble("FirstWin");
			marker.setFirstWin(FirstWin);
			
			double FirstDraw = Data.getDouble("FirstDraw");
			marker.setFirstDraw(FirstDraw);
			
			double FirstLoss = Data.getDouble("FirstLoss");
			marker.setFirstLoss(FirstLoss);
			
			double LastWin = Data.getDouble("LastWin");
			marker.setFirstLoss(LastWin);
			
			double LastDraw = Data.getDouble("LastDraw");
			marker.setFirstLoss(LastDraw);
			
			double LastLoss = Data.getDouble("LastLoss");
			marker.setFirstLoss(LastLoss);
			
			double LastChangeWin = Data.getDouble("LastChangeWin");
			marker.setFirstLoss(LastChangeWin);
			
			double LastChangeDraw = Data.getDouble("LastChangeDraw");
			marker.setFirstLoss(LastChangeDraw);
			
			double LastChangeLoss = Data.getDouble("LastChangeLoss");
			marker.setFirstLoss(LastChangeLoss);
			list.add(marker);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static void checkEuropOdds(String mid) throws Exception {
		ArrayList<EuropMarker> list1 = getEuropOdds(mid);
		Collections.sort(list1, new SortById());

		GetConnectionSqlServer getConn = new GetConnectionSqlServer();
		Connection conn = getConn.getConnectionSqlServer();

		String sql = "select ISNULL(B.NAME_CHS,B.NAME) BookMakerName,A.* FROM [TS_EUROPEAN_OFFER] A inner JOIN dbo.TS_BOOKMAKER B ON B.BOOKMAKER_ID = A.BOOKMAKER_ID WHERE match_id in (select match_id FROM [TS_MATCH] WHERE ss_match_id ='"
				+ mid + "')";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String BookMarkerName = rs.getString("BookMakerName");
			String BookMarkerId = rs.getString("BOOKMAKER_ID");
			String FirstWin = rs.getString("FIRST_WIN");
			String FirstDraw = rs.getString("FIRST_DRAW");
			String FirstLoss = rs.getString("FIRST_LOSS");
			String LastWin = rs.getString("LAST_WIN");
			String LastDraw = rs.getString("LAST_DRAW");
			String LastLoss = rs.getString("LAST_LOSS");
			String LastChangeWin = rs.getString("LAST_CHANGE_WIN");
			String LastChangeDraw = rs.getString("LAST_CHANGE_DRAW");
			String LastChangeLoss = rs.getString("LAST_CHANGE_LOSS");

			for (int i = 0; i < list1.size(); i++) {
				EuropMarker m = list1.get(i);
				if (BookMarkerId.equals(String.valueOf(m.getBookMarkerId()))) {
					if (BookMarkerName.equals(String.valueOf(m.getBookMarkerName()))) {
						System.out.println("0,0");
					} else {
						System.out.println(mid + "," + String.valueOf(m.getBookMarkerName()));
					}
					System.out.println(FirstWin + "," + String.valueOf(m.getFirstWin()));
					System.out.println(FirstDraw + "," + String.valueOf(m.getFirstDraw()));
					System.out.println(FirstLoss + "," + String.valueOf(m.getFirstLoss()));
					System.out.println(LastWin + "," + String.valueOf(m.getLastWin()));
					System.out.println(LastDraw + "," + String.valueOf(m.getLastDraw()));
					System.out.println(LastLoss + "," + String.valueOf(m.getLastLoss()));
					System.out.println(LastChangeWin + "," + String.valueOf(m.getLastChangeWin()));
					System.out.println(LastChangeDraw + "," + String.valueOf(m.getLastChangeDraw()));
					System.out.println(LastChangeLoss + "," + String.valueOf(m.getLastChangeLoss()));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void checkAsianOdds(String mid) throws Exception {
		ArrayList<AsianMarker> list1 = getAsianOdds(mid);
		Collections.sort(list1, new SortById());

		GetConnectionSqlServer getConn = new GetConnectionSqlServer();
		Connection conn = getConn.getConnectionSqlServer();

		String sql = "select ISNULL(B.NAME_CHS,B.NAME) BookMakerName,A.* FROM [TS_ASIATIC_OFFER] A inner JOIN dbo.TS_BOOKMAKER B ON B.BOOKMAKER_ID = A.BOOKMAKER_ID WHERE match_id in (select match_id FROM [TS_MATCH] WHERE ss_match_id ='"
				+ mid + "')";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String BookMarkerName = rs.getString("BookMakerName");
			String BookMarkerId = rs.getString("BOOKMAKER_ID");
//			String FirstWin = rs.getString("FIRST_WIN");
//			String FirstDraw = rs.getString("FIRST_DRAW");
//			String FirstLoss = rs.getString("FIRST_LOSS");
//			String LastWin = rs.getString("LAST_WIN");
//			String LastDraw = rs.getString("LAST_DRAW");
//			String LastLoss = rs.getString("LAST_LOSS");
//			String LastChangeWin = rs.getString("LAST_CHANGE_WIN");
//			String LastChangeDraw = rs.getString("LAST_CHANGE_DRAW");
//			String LastChangeLoss = rs.getString("LAST_CHANGE_LOSS");

			for (int i = 0; i < list1.size(); i++) {
				AsianMarker m = list1.get(i);
				if (BookMarkerId.equals(String.valueOf(m.getBookMarkerId()))) {
					if (BookMarkerName.equals(String.valueOf(m.getBookMarkerName()))) {
						System.out.println("0,0");
					} else {
						System.out.println(mid + "," + String.valueOf(m.getBookMarkerName()));
					}
				}
			}
		}
	}
	
	

	public static void main(String[] args) throws Exception {
		getMatchs("2017-08-11");
		getMatchs("2017-08-12");
		getMatchs("2017-08-13");
		getMatchs("2017-08-14");
		getMatchs("2017-08-15");
		getMatchs("2017-08-16");
		
//		File log=new File("E:\\OddsLog.txt");
//		if (!log.exists()) {
//			log.createNewFile();
//		}
//		FileOutputStream fileOutputStream = new FileOutputStream(log);
//		PrintStream printStream = new PrintStream(fileOutputStream);
//		System.setOut(printStream);
//		
//		String arr[] = { "2016-09-29", "2016-09-30", "2016-09-31" };
//		int count = 0;
//		int flag = 0;
//		while (true) {
//			for (int n = 0; n < arr.length; n++) {
//				String issue = arr[n];
//				ArrayList<Match> list = getMatchs(issue);
//				if (list != null) {
//					for (int i = 0; i < list.size(); i++) {
//						Match m = list.get(i);
//						System.out.println(m.getMId() + "\t");
//						String MId = String.valueOf(m.getMId());
//						ArrayList<EuropMarker> list1 = getEuropOdds(MId);
//						count++;
//						System.out.println("第 " + count + "请求");
//						for (int j = 0; j < list1.size(); j++) {
//							EuropMarker marker = list1.get(j);
//
//							String name = marker.getBookMarkerName();
//							if (name == null || name.equals("null")) {
//								flag++;
//								System.out.print(MId + " " + marker.toString());
//								break;
//							}
//						}
//						System.out.print("复现 " + flag + "次\n");
//						Thread.sleep(5000);
//						
//						ArrayList<AsianMarker> list2 = getAsianOdds(MId);
//						count++;
//						System.out.println("第 " + count + "请求");
//						for (int j = 0; j < list2.size(); j++) {
//							AsianMarker marker = list2.get(j);
//
//							String name = marker.getBookMarkerName();
//							if (name == null || name.equals("null")) {
//								flag++;
//								System.out.print(MId + " " + marker.toString());
//								break;
//							}
//						}
//						System.out.print("复现 " + flag + "次\n");
//						Thread.sleep(5000);
//					}
//				}
//			}
//		}
	}
}

@SuppressWarnings("rawtypes")
class SortById implements Comparator {
	public int compare(Object o1, Object o2) {
		EuropMarker s1 = (EuropMarker) o1;
		EuropMarker s2 = (EuropMarker) o2;
		int id1 = (int) s1.getBookMarkerId();
		int id2 = (int) s2.getBookMarkerId();
		if (id1 > id2) {
			return 1;
		} else {
			return 0;
		}
	}
}

@SuppressWarnings("rawtypes")
class SortByBMId implements Comparator {
	public int compare(Object o1, Object o2) {
		AsianMarker s1 = (AsianMarker) o1;
		AsianMarker s2 = (AsianMarker) o2;
		int id1 = (int) s1.getBookMarkerId();
		int id2 = (int) s2.getBookMarkerId();
		if (id1 > id2) {
			return 1;
		} else {
			return 0;
		}
	}
}
