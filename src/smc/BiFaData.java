package smc;

import java.util.ArrayList;
import java.util.Date;

import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class BiFaData {
	static Date d = new Date();

	// 必发总交易分析数据
	private static String url1 = DataUrls.ft_bifa;

	// private static String url2 =
	// "https://smc.jdddata.com/api/betfair/getbftradeinfo?type=2&matchid=";

	static ArrayList<String> list = new ArrayList<String>();

	public static String getBiFaData(String matchId) throws Exception {
		String str = "";
		String URL = url1 + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		int code = obj.getInt("code");
		if (code != 0) {
			System.out.println("code = 0, 无必发数据");
		} else {
			JSONObject data = obj.getJSONObject("data");
			JSONObject tradeBo = data.getJSONObject("tradePieChartBo");

			String drawPercent = tradeBo.getString("drawPercent");
			String lossPercent = tradeBo.getString("lossPercent");
			String winPercent = tradeBo.getString("winPercent");

			JSONArray betList = data.getJSONArray("betFairTradeStatisticsBoList");

			String lossProfit = "";
			String drawProfit = "";
			String winProfit = "";

			for (int i = 0; i < betList.size(); i++) {
				JSONObject bet = (JSONObject) betList.get(i);
				String typeId = bet.getString("typeId");
				if (typeId.equals("0")) {
					lossProfit = bet.getString("bankerProfit");
				} else if (typeId.equals("1")) {
					drawProfit = bet.getString("bankerProfit");
				} else if (typeId.equals("3")) {
					winProfit = bet.getString("bankerProfit");
				}
			}

			if (winPercent.equals("") || drawPercent.equals("") || lossPercent.equals("")) {
				str = "没有必发数据";
			} else {
				System.out.println("\t" + "主客交易：" + winPercent + "\t" + drawPercent + "\t" + lossPercent);
//				String result = getHotBifa(winPercent, drawPercent, lossPercent);
//				if (winPercent.contains(result)) {
//					str = "主队必发交易占比极大，如果取胜，则庄家盈亏" + winProfit + "\t 主队交易占比：" + winPercent;
//				} else if (drawPercent.contains(result)) {
//					str = "平必发交易占比极大，如果取胜，则庄家盈亏" + drawProfit + "\t 平交易占比：" + drawPercent;
//				} else if (lossPercent.contains(result)) {
//					str = "客队必发交易占比极大，如果取胜，则庄家盈亏" + lossProfit + "\t 客队交易占比：" + lossPercent;
//				}
			}
		}
		return str;
	}

	public static String getHotCoolData(String matchId) throws Exception {
		String str = "";
		String URL = url1 + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		int code = obj.getInt("code");
		if (code != 0) {
			System.out.println("code = 0, 无必发数据");
		} else {
			JSONObject data = obj.getJSONObject("data");

			JSONArray betList = data.getJSONArray("betFairTradeStatisticsBoList");

			String lossIndex = "";
			String drawIndex = "";
			String winIndex = "";

			for (int i = 0; i < betList.size(); i++) {
				JSONObject bet = (JSONObject) betList.get(i);
				String typeId = bet.getString("typeId");
				if (typeId.equals("0")) {
					lossIndex = bet.getString("caloricIndex");
				} else if (typeId.equals("1")) {
					drawIndex = bet.getString("caloricIndex");
				} else if (typeId.equals("3")) {
					winIndex = bet.getString("caloricIndex");
				}
			}
			str =  "\t" + "冷热指数：" + "主胜 " + winIndex + "\t" + "平 " + drawIndex + "\t" + "客胜 " + lossIndex; 
		}
		return str;
	}
	
	public static String getBiFaPrice(String matchId) throws Exception {
		String str = "";
		String URL = url1 + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		int code = obj.getInt("code");
		if (code != 0) {
			System.out.println("code = 0, 无必发数据");
		} else {
			JSONObject data = obj.getJSONObject("data");

			JSONArray betList = data.getJSONArray("betFairTradeStatisticsBoList");

			String lossprice = "";
			String drawprice = "";
			String winprice = "";

			for (int i = 0; i < betList.size(); i++) {
				JSONObject bet = (JSONObject) betList.get(i);
				String typeId = bet.getString("typeId");
				if (typeId.equals("0")) {
					lossprice = bet.getString("price");
					if (lossprice.substring(lossprice.length() -1, lossprice.length()).equals("0")) {
						lossprice = lossprice.substring(0,lossprice.length() -1);
					}
				} else if (typeId.equals("1")) {
					drawprice = bet.getString("price");
					if (drawprice.substring(drawprice.length() -1, drawprice.length()).equals("0")) {
						drawprice = drawprice.substring(0,drawprice.length() -1);
					}
				} else if (typeId.equals("3")) {
					winprice = bet.getString("price");
					if (winprice.substring(winprice.length() -1, winprice.length()).equals("0")) {
						winprice = winprice.substring(0,winprice.length() -1);
					}
				}
			}
			
			str = "\t" + "必发价位：" + winprice + "\t" + drawprice + "\t" + lossprice;
		}
		return str;
	}
	
	public static String getBiFaAmount(String matchId) throws Exception {
		String str = "";
		String URL = url1 + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		int code = obj.getInt("code");
		if (code != 0) {
			System.out.println("code = 0, 无必发数据");
		} else {
			JSONObject data = obj.getJSONObject("data");

			JSONArray betList = data.getJSONArray("betFairTradeStatisticsBoList");
			
			String lossAmount = "";
			String drawAmount = "";
			String winAmount = "";

			for (int i = 0; i < betList.size(); i++) {
				JSONObject bet = (JSONObject) betList.get(i);
				String typeId = bet.getString("typeId");
				if (typeId.equals("0")) {
					lossAmount = bet.getString("amount");
				} else if (typeId.equals("1")) {
					drawAmount = bet.getString("amount");
				} else if (typeId.equals("3")) {
					winAmount = bet.getString("amount");
				}
			}
			
			float amount = Long.valueOf(lossAmount) +  Long.valueOf(drawAmount) + Long.valueOf(winAmount);
			str = "\t" + "成交量：" + Long.valueOf(winAmount)/amount + "\t" +  Long.valueOf(drawAmount)/amount + "\t" +  Long.valueOf(lossAmount)/amount;
		}
		return str;
	}
	
	public static String getBiFaBig(String matchId) throws Exception {
		String str = "";
		String URL = DataUrls.ft_bifaBig + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		int code = obj.getInt("code");
		if (code != 0) {
			System.out.println("code = 0, 无必发数据");
		} else {
			JSONObject data = obj.getJSONObject("data");
			JSONObject betBo = data.getJSONObject("betFairTradePieChartBo");
			String winPercent = betBo.getString("winPercent");
			String drawPercent = betBo.getString("drawPercent");
			String lossPercent = betBo.getString("lossPercent");
			
			str = "\t" + "大额交易：" + winPercent + "\t" + drawPercent + "\t" + lossPercent;
		}
		return str;
	}

	public static String getHotBifa(String win, String draw, String loss) {

		float w = Float.valueOf(win.substring(0, win.length() - 1));
		float d = Float.valueOf(draw.substring(0, draw.length() - 1));
		float l = Float.valueOf(loss.substring(0, loss.length() - 1));

		return String.valueOf((w > d ? w : d) > l ? (w > d ? w : d) : l);
	}

	public static void main(String[] args) throws Exception {
		String matchId = "12330576";
//		System.out.println(getBiFaAmount(matchId));
		System.out.println(getBiFaPrice(matchId));
		System.out.println(getBiFaData(matchId));
//		System.out.println(getBiFaBig(matchId));
		System.out.println(getHotCoolData(matchId));
		
//		// 获取期次
//		String issues = JzIssue.getJZIssues();
//		if (!issues.equals("")) {
//			String[] issue = issues.split(",");
//			for (int i = 0; i < issue.length; i++) {
//				// 获取对阵
//				String matchs = JzMatchs.getJZMatchs(issue[i]);
//				if (!matchs.equals("")) {
//					String[] match = matchs.split(",");
//					for (int j = 0; j < match.length; j++) {
//						HeadTest.getTeamName(match[j]);
//						getBiFaData(match[j]);
//					}
//				}
//			}
//		}
	}
}
