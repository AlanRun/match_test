package smc;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

/**
 * 验证smc PC平均欧赔接口是否与app一致，
 * 
 * @author Alan Huang
 *
 */
public class PcSPTest {
	static Date d = new Date();

	// 竞足头部接口
	private static String url_common = "https://smc.jdd.com/api/odds/getpceurooddslist";
	private static String url_eu = "https://smc.jdd.com/api/odds/geteurooddslist?lotteryId=90&pcode=h5&pts=0&version=v2.1&matchid=";
	private static String params_0 = "{\"issueIds\":\"{\\\"2017-08-31\\\":[\\\"9291681\\\",\\\"9291613\\\",\\\"9291615\\\",\\\"9291677\\\",\\\"9291611\\\",\\\"9684467\\\",\\\"9684233\\\",\\\"7678180\\\",\\\"7678182\\\",\\\"7678184\\\",\\\"7678236\\\",\\\"7678238\\\",\\\"7678240\\\",\\\"9732507\\\",\\\"7678608\\\",\\\"7678610\\\",\\\"12239776\\\",\\\"7696092\\\",\\\"7696088\\\",\\\"7696096\\\",\\\"7696090\\\",\\\"7696094\\\"],\\\"2017-09-01\\\":[\\\"7678416\\\",\\\"12068690\\\",\\\"11842970\\\",\\\"11842978\\\",\\\"11842984\\\",\\\"11842974\\\",\\\"11842976\\\",\\\"11842980\\\",\\\"11842982\\\",\\\"11842968\\\",\\\"12344142\\\",\\\"7678290\\\",\\\"7678292\\\",\\\"7678294\\\",\\\"7678418\\\",\\\"7678420\\\",\\\"7678510\\\",\\\"7678514\\\",\\\"10160897\\\",\\\"10160901\\\",\\\"12344144\\\",\\\"12344154\\\",\\\"10160899\\\"]}\",\"bookId\":0,\"lotteryId\":90,\"pcode\":\"pc\"}";
	private static String params_3000021 = "{\"issueIds\":\"{\\\"2017-08-31\\\":[\\\"9291681\\\",\\\"9291613\\\",\\\"9291615\\\",\\\"9291677\\\",\\\"9291611\\\",\\\"9684467\\\",\\\"9684233\\\",\\\"7678180\\\",\\\"7678182\\\",\\\"7678184\\\",\\\"7678236\\\",\\\"7678238\\\",\\\"7678240\\\",\\\"9732507\\\",\\\"7678608\\\",\\\"7678610\\\",\\\"12239776\\\",\\\"7696092\\\",\\\"7696088\\\",\\\"7696096\\\",\\\"7696090\\\",\\\"7696094\\\"],\\\"2017-09-01\\\":[\\\"7678416\\\",\\\"12068690\\\",\\\"11842970\\\",\\\"11842978\\\",\\\"11842984\\\",\\\"11842974\\\",\\\"11842976\\\",\\\"11842980\\\",\\\"11842982\\\",\\\"11842968\\\",\\\"12344142\\\",\\\"7678290\\\",\\\"7678292\\\",\\\"7678294\\\",\\\"7678418\\\",\\\"7678420\\\",\\\"7678510\\\",\\\"7678514\\\",\\\"10160897\\\",\\\"10160901\\\",\\\"12344144\\\",\\\"12344154\\\",\\\"10160899\\\"]}\",\"bookId\":3000021,\"lotteryId\":90,\"pcode\":\"pc\"}";
	private static String params_3000068 = "{\"issueIds\":\"{\\\"2017-08-31\\\":[\\\"9291681\\\",\\\"9291613\\\",\\\"9291615\\\",\\\"9291677\\\",\\\"9291611\\\",\\\"9684467\\\",\\\"9684233\\\",\\\"7678180\\\",\\\"7678182\\\",\\\"7678184\\\",\\\"7678236\\\",\\\"7678238\\\",\\\"7678240\\\",\\\"9732507\\\",\\\"7678608\\\",\\\"7678610\\\",\\\"12239776\\\",\\\"7696092\\\",\\\"7696088\\\",\\\"7696096\\\",\\\"7696090\\\",\\\"7696094\\\"],\\\"2017-09-01\\\":[\\\"7678416\\\",\\\"12068690\\\",\\\"11842970\\\",\\\"11842978\\\",\\\"11842984\\\",\\\"11842974\\\",\\\"11842976\\\",\\\"11842980\\\",\\\"11842982\\\",\\\"11842968\\\",\\\"12344142\\\",\\\"7678290\\\",\\\"7678292\\\",\\\"7678294\\\",\\\"7678418\\\",\\\"7678420\\\",\\\"7678510\\\",\\\"7678514\\\",\\\"10160897\\\",\\\"10160901\\\",\\\"12344144\\\",\\\"12344154\\\",\\\"10160899\\\"]}\",\"bookId\":3000068,\"lotteryId\":90,\"pcode\":\"pc\"}";
	private static String params_3000048 = "{\"issueIds\":\"{\\\"2017-08-31\\\":[\\\"9291681\\\",\\\"9291613\\\",\\\"9291615\\\",\\\"9291677\\\",\\\"9291611\\\",\\\"9684467\\\",\\\"9684233\\\",\\\"7678180\\\",\\\"7678182\\\",\\\"7678184\\\",\\\"7678236\\\",\\\"7678238\\\",\\\"7678240\\\",\\\"9732507\\\",\\\"7678608\\\",\\\"7678610\\\",\\\"12239776\\\",\\\"7696092\\\",\\\"7696088\\\",\\\"7696096\\\",\\\"7696090\\\",\\\"7696094\\\"],\\\"2017-09-01\\\":[\\\"7678416\\\",\\\"12068690\\\",\\\"11842970\\\",\\\"11842978\\\",\\\"11842984\\\",\\\"11842974\\\",\\\"11842976\\\",\\\"11842980\\\",\\\"11842982\\\",\\\"11842968\\\",\\\"12344142\\\",\\\"7678290\\\",\\\"7678292\\\",\\\"7678294\\\",\\\"7678418\\\",\\\"7678420\\\",\\\"7678510\\\",\\\"7678514\\\",\\\"10160897\\\",\\\"10160901\\\",\\\"12344144\\\",\\\"12344154\\\",\\\"10160899\\\"]}\",\"bookId\":3000048,\"lotteryId\":90,\"pcode\":\"pc\"}";

	public static void getPcEuSp() throws Exception {
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendPost(url_common, params_0);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject issue = (JSONObject) data.get(i);
			JSONArray matchs = issue.getJSONArray("issueOddsList");
			for (int j = 0; j < matchs.size(); j++) {
				JSONObject match = (JSONObject) matchs.get(j);
				String id =  match.getString("matchId");
				JSONObject providerOdds = match.getJSONObject("providerOdds");
				String win = providerOdds.getString("win");
				String draw = providerOdds.getString("draw");
				String loss = providerOdds.getString("loss");
				Date d = new Date();
				System.out.println(d.toString());
				System.out.println(id + " pc的平均欧赔: " +win + "  " + draw + "  " + loss);
				
				request = new HttpRequester();
				request.setDefaultContentEncoding("UTF-8");
				hr = request.sendGet(url_eu+id);
				json = hr.getContent();
				obj = JSONObject.fromObject(json);
				JSONArray data1 = obj.getJSONArray("data");
				for (int k = 0; k < data1.size(); k++) {
					JSONObject bk = (JSONObject) data1.get(k);
					int bookMarkerId = bk.getInt("bookMarkerId");
					if (bookMarkerId == 0) {
						String lastWin = bk.getString("lastWin");
						String lastDraw = bk.getString("lastDraw");
						String lastLoss = bk.getString("lastLoss");
						if ((!win.equals(lastWin)) || (!draw.equals(lastDraw)) || (!loss.equals(lastLoss))) {
							System.err.println(id + " app平均欧赔  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						} else {
							System.out.println(id + " app平均欧赔  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						}
						break;
					} else {
						continue;
					}
				}
				System.out.println();
			}
		}
	}
	
	public static void getPc21Sp() throws Exception {
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendPost(url_common, params_3000021);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject issue = (JSONObject) data.get(i);
			JSONArray matchs = issue.getJSONArray("issueOddsList");
			for (int j = 0; j < matchs.size(); j++) {
				JSONObject match = (JSONObject) matchs.get(j);
				String id =  match.getString("matchId");
				JSONObject providerOdds = match.getJSONObject("providerOdds");
				String win = providerOdds.getString("win");
				String draw = providerOdds.getString("draw");
				String loss = providerOdds.getString("loss");
				Date d = new Date();
				System.out.println(d.toString());
				System.out.println(id + " pc的威廉希尓: " +win + "  " + draw + "  " + loss);
				
				request = new HttpRequester();
				request.setDefaultContentEncoding("UTF-8");
				hr = request.sendGet(url_eu+id);
				json = hr.getContent();
				obj = JSONObject.fromObject(json);
				JSONArray data1 = obj.getJSONArray("data");
				for (int k = 0; k < data1.size(); k++) {
					JSONObject bk = (JSONObject) data1.get(k);
					int bookMarkerId = bk.getInt("bookMarkerId");
					if (bookMarkerId == 3000021) {
						String lastWin = bk.getString("lastWin");
						String lastDraw = bk.getString("lastDraw");
						String lastLoss = bk.getString("lastLoss");
						if ((!win.equals(lastWin)) || (!draw.equals(lastDraw)) || (!loss.equals(lastLoss))) {
							System.err.println(id + " app威廉希尓  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						} else {
							System.out.println(id + " app威廉希尓  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						}
						break;
					} else {
						continue;
					}
				}
				System.out.println();
			}
		}
	}
	
	public static void getPc68Sp() throws Exception {
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendPost(url_common, params_3000068);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject issue = (JSONObject) data.get(i);
			JSONArray matchs = issue.getJSONArray("issueOddsList");
			for (int j = 0; j < matchs.size(); j++) {
				JSONObject match = (JSONObject) matchs.get(j);
				String id =  match.getString("matchId");
				JSONObject providerOdds = match.getJSONObject("providerOdds");
				String win = providerOdds.getString("win");
				String draw = providerOdds.getString("draw");
				String loss = providerOdds.getString("loss");
				Date d = new Date();
				System.out.println(d.toString());
				System.out.println(id + " pc的立博: " +win + "  " + draw + "  " + loss);
				
				request = new HttpRequester();
				request.setDefaultContentEncoding("UTF-8");
				hr = request.sendGet(url_eu+id);
				json = hr.getContent();
				obj = JSONObject.fromObject(json);
				JSONArray data1 = obj.getJSONArray("data");
				for (int k = 0; k < data1.size(); k++) {
					JSONObject bk = (JSONObject) data1.get(k);
					int bookMarkerId = bk.getInt("bookMarkerId");
					if (bookMarkerId == 3000068) {
						String lastWin = bk.getString("lastWin");
						String lastDraw = bk.getString("lastDraw");
						String lastLoss = bk.getString("lastLoss");
						if ((!win.equals(lastWin)) || (!draw.equals(lastDraw)) || (!loss.equals(lastLoss))) {
							System.err.println(id + " app立博  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						} else {
							System.out.println(id + " app立博  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						}
						break;
					} else {
						continue;
					}
				}
				System.out.println();
			}
		}
	}
	
	public static void getPc48Sp() throws Exception {
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendPost(url_common, params_3000048);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			
		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			JSONObject issue = (JSONObject) data.get(i);
			JSONArray matchs = issue.getJSONArray("issueOddsList");
			for (int j = 0; j < matchs.size(); j++) {
				JSONObject match = (JSONObject) matchs.get(j);
				String id =  match.getString("matchId");
				JSONObject providerOdds = match.getJSONObject("providerOdds");
				String win = providerOdds.getString("win");
				String draw = providerOdds.getString("draw");
				String loss = providerOdds.getString("loss");
				Date d = new Date();
				System.out.println(d.toString());
				System.out.println(id + " pc的伟德: " +win + "  " + draw + "  " + loss);
				
				request = new HttpRequester();
				request.setDefaultContentEncoding("UTF-8");
				hr = request.sendGet(url_eu+id);
				json = hr.getContent();
				obj = JSONObject.fromObject(json);
				JSONArray data1 = obj.getJSONArray("data");
				for (int k = 0; k < data1.size(); k++) {
					JSONObject bk = (JSONObject) data1.get(k);
					int bookMarkerId = bk.getInt("bookMarkerId");
					if (bookMarkerId == 3000048) {
						String lastWin = bk.getString("lastWin");
						String lastDraw = bk.getString("lastDraw");
						String lastLoss = bk.getString("lastLoss");
						if ((!win.equals(lastWin)) || (!draw.equals(lastDraw)) || (!loss.equals(lastLoss))) {
							System.err.println(id + " app伟德  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						} else {
							System.out.println(id + " app伟德  : " +lastWin + "  " + lastDraw + "  " + lastLoss);
						}
						break;
					} else {
						continue;
					}
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		getPcEuSp();
		getPc21Sp();
		getPc48Sp();
		getPc68Sp();
		
//		Date d = new Date();
//		System.out.println(d.toString());
//
//		// 获取期次
//		String issues = IssueTest.getJZIssues();
//		if (!issues.equals("")) {
//			String[] issue = issues.split(",");
//			for (int i = 0; i < issue.length; i++) {
//				// 获取对阵
//				System.out.println("=========" + issue[i] + "=========");
//				ArrayList<FootballMatch> list = MatchsTest.getMatchList(issue[i]);
//				for (int j = 0; j < list.size(); j++) {
//					FootballMatch match = list.get(j);
//					checkHead(match);
//				}
//				System.out.println("=========" + issue[i] + "=========\n");
//			}
//		}
	}
}
