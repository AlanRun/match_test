package smc;

import java.io.IOException;

import helper.DataUrls;
import helper.JzMatchSpInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class SmcTest {
	
	public static String getTeamName(String matchId) throws Exception {
		String URL = DataUrls.ft_head + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		String homeTeamName = data.getString("homeTeamName");
		String awayTeamName = data.getString("awayTeamName");
		String unName = data.getString("uniqueTournamentName");
		String matchTime = data.getString("matchTime");
		
		JzMatchSpInfo matchInfo = new JzMatchSpInfo();
		matchInfo.setMatchID(matchId);
		matchInfo.setHTeam(homeTeamName);
		matchInfo.setVTeam(awayTeamName);
		
		String result = matchId + "\t" + unName + "\t" + homeTeamName + "\t" + awayTeamName + "\t" + matchTime;
		return result;
	}
	
	public static String getJCSp(String matchId) throws Exception {
		String str = "";
		String URL = DataUrls.ft_euroList + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		JSONArray oddList = data.getJSONArray("oddsEuroDetailCurrChangeBoList");
		JSONObject odd = (JSONObject) oddList.get(0);
		String winRate = odd.getString("winRate");
		String drawRate = odd.getString("drawRate");
		String lossRate = odd.getString("lossRate");
		str = winRate + "\t" + drawRate + "\t" + lossRate;
		return str;
	}
	
	public static String getEuroAveSp(String matchId) throws Exception {
		String str = "";
		String URL = DataUrls.ft_euroList + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		JSONObject ave = (JSONObject) data.get(0);
		String lastWin = ave.getString("lastWin");
		String lastDraw = ave.getString("lastDraw");
		String lastLoss = ave.getString("lastLoss");
		
		str = "\t" + "百家平均：" + lastWin + "\t" + lastDraw + "\t" + lastLoss;
		return str;
	}
	
	public static String getTeamRank(String mid) throws IOException {
		String url = DataUrls.ft_teamrank;
		
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(url + mid);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			// System.out.println(json);
		}
		String NMm = "";

		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		// dataType 1:联赛积分榜, 2:杯赛-小组赛(俱乐部首轮&非首轮), 3:杯赛-淘汰赛&友谊赛-俱乐部, 4:友谊赛-国家& 世界杯小组首轮
		String dataType = data.getString("dataType");
		
		// 实体内部状态码1：有数据; 0：无数据 = ['1', '0']integerEnum:1, 0,
		int innerCode = data.getInt("innerCode");
		if (innerCode == 0) {
			NMm = "No team rank";
			System.out.println(NMm);
			return NMm;
		}
		
		String homeTeamId = data.getString("homeTeamId");
		String awayTeamId = data.getString("awayTeamId");
		JSONObject sub1Bo = data.getJSONObject("sub1Bo");
		JSONObject sub2Bo = data.getJSONObject("sub2Bo");
		JSONObject sub3Bo = data.getJSONObject("sub3Bo");
		JSONObject sub4Bo = data.getJSONObject("sub4Bo");

		String homeS = "";
		String awayS = "";

		switch (dataType) {
		case "1":
			homeS = sub1Bo.toString();
			awayS = sub1Bo.toString();
			break;
		case "2":
			homeS = sub2Bo.toString();
			awayS = sub2Bo.toString();
			break;
		case "3":
			homeS = sub3Bo.toString();
			awayS = sub3Bo.toString();
			break;
		case "4":
			homeS = sub4Bo.toString();
			awayS = sub4Bo.toString();
			break;
		default:
			break;
		}

		JSONObject homeObj = JSONObject.fromObject(homeS);
		JSONObject awayObj = JSONObject.fromObject(awayS);

		if (dataType.equals("1") || dataType.equals("2")) {
			String groupName = "";
			if (dataType.equals("2")) {
				groupName = homeObj.getString("groupName");
			}

			JSONArray rankingBoList = homeObj.getJSONArray("rankingBoList");
			for (int i = 0; i < rankingBoList.size(); i++) {
				JSONObject rank = (JSONObject) rankingBoList.get(i);

				if (dataType.equals("1")) {
					groupName = rank.getString("tournamentName");
				}

				String teamId = rank.getString("teamId");
				if (teamId.equals(homeTeamId)) {
					String rankingNum = rank.getString("rankingNum");
					NMm = groupName + "  " + rankingNum;
					break;
				}
			}

			rankingBoList = awayObj.getJSONArray("rankingBoList");
			for (int i = 0; i < rankingBoList.size(); i++) {
				JSONObject rank = (JSONObject) rankingBoList.get(i);
				String teamId = rank.getString("teamId");
				if (teamId.equals(awayTeamId)) {
					String rankingNum = rank.getString("rankingNum");
					NMm = NMm + ",\t" + groupName + "  " + rankingNum;
					break;
				}
			}
		} else if (dataType.equals("3")) {
			JSONObject homeRankingBo = homeObj.getJSONObject("homeRankingBo");
			JSONArray rankingBoList = homeRankingBo.getJSONArray("rankingBoList");
			String groupName = homeRankingBo.getString("groupName");
			for (int i = 0; i < rankingBoList.size(); i++) {
				JSONObject rank = (JSONObject) rankingBoList.get(i);
				String teamId = rank.getString("teamId");
				if (teamId.equals(homeTeamId)) {
					String rankingNum = rank.getString("rankingNum");
					NMm = groupName + "  " + rankingNum;
					break;
				}
			}

			JSONObject awayRankingBo = homeObj.getJSONObject("awayRankingBo");
			rankingBoList = awayRankingBo.getJSONArray("rankingBoList");
			groupName = homeRankingBo.getString("groupName");
			for (int i = 0; i < rankingBoList.size(); i++) {
				JSONObject rank = (JSONObject) rankingBoList.get(i);
				String teamId = rank.getString("teamId");
				if (teamId.equals(awayTeamId)) {
					String rankingNum = rank.getString("rankingNum");
					NMm = NMm + "\t" + groupName + "  " + rankingNum;
					break;
				}
			}
		}

		System.out.println(NMm);
		return NMm;
	}
	
	public static void getMatchFullInfo(String matchId) throws Exception{
		String url = DataUrls.ft_matchInfo;
		
		HttpRequester request = new HttpRequester();
//		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(url + matchId);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
//			 System.out.println(json);
		}
		
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		int innerCode1 = data.getInt("innerCode");
		String homeTeamName = data.getString("homeTeamName");
		String awayTeamName = data.getString("awayTeamName");
		System.out.println(matchId + " " + homeTeamName + " VS " + awayTeamName);
		if (innerCode1 == 0) {
			System.out.println(" 没有数据情报数据");
		} else {
			
			JSONObject meetingBo = data.getJSONObject("meetingBo");
			int innerCode2 = meetingBo.getInt("innerCode");
			if (innerCode2 == 0) {
				System.out.println("没有交战数据");
			} else {
				String homeWinRate = meetingBo.getString("homeWinRate");
				String awayWinRate = meetingBo.getString("awayWinRate");
				if (checkRate(homeWinRate, awayWinRate)) {
					System.err.println("交战概率有误");
					System.err.println(homeWinRate + " " + awayWinRate + "\n");
				}
			}
			
			JSONObject fightingBo = data.getJSONObject("fightingBo");
			
			JSONObject fightingHBo = fightingBo.getJSONObject("fightingHBo");
			String hwin = fightingHBo.getString("winRate");
			int innerCode3 = fightingHBo.getInt("innerCode");
			if (innerCode3 == 0) {
				System.out.println("主队没有战力数据");
			}
			
			JSONObject fightingABo = fightingBo.getJSONObject("fightingABo");
			String awin = fightingABo.getString("winRate");
			int innerCode4 = fightingABo.getInt("innerCode");
			if (innerCode4 == 0) {
				System.out.println("客队没有战力数据");
			}
			if (checkRate(hwin, awin)) {
				System.err.println("战力概率有误");
				System.err.println(hwin + " " + awin + "\n");
			}
			
			JSONObject recentBo = data.getJSONObject("recentBo");
			JSONObject recentHBo = recentBo.getJSONObject("recentHBo");
			hwin = recentHBo.getString("winRate");
			int innerCode5 = recentHBo.getInt("innerCode");
			if (innerCode5 == 0) {
				System.out.println("主队没有状态伤停数据");
			}
			JSONObject recentABo = recentBo.getJSONObject("recentABo");
			awin = recentABo.getString("winRate");
			int innerCode6 = recentABo.getInt("innerCode");
			if (innerCode6 == 0) {
				System.out.println("客队没有状态伤停数据");
			}
			if (checkRate(hwin, awin)) {
				System.err.println("战力概率有误");
				System.err.println(hwin + " " + awin);
			}
			
			JSONObject oddsBo = data.getJSONObject("oddsBo");
			int innerCode7 = oddsBo.getInt("innerCode");
			if (innerCode7 == 0 ) {
				System.out.println("沒有赔率必发数据");
			}
			
			String homeWinRate = oddsBo.getString("homeWinRate");
			String awayWinRate = oddsBo.getString("awayWinRate");
			
			if (checkRate(homeWinRate, awayWinRate)) {
				System.err.println("赔率必发概率有误");
				System.err.println(homeWinRate + " " + awayWinRate + "\n");
			}
		}
		System.out.println();
	}
	
	public static String getBiFaData(String matchId) throws Exception {
		String str = "";
		String URL = DataUrls.ft_bifa + matchId;

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
				String result = getHotBifa(winPercent, drawPercent, lossPercent);
				if (winPercent.contains(result)) {
					str = "主队必发交易占比极大，如果取胜，则庄家盈亏" + winProfit + "\t 主队交易占比：" + winPercent;
				} else if (drawPercent.contains(result)) {
					str = "平必发交易占比极大，如果取胜，则庄家盈亏" + drawProfit + "\t 平交易占比：" + drawPercent;
				} else if (lossPercent.contains(result)) {
					str = "客队必发交易占比极大，如果取胜，则庄家盈亏" + lossProfit + "\t 客队交易占比：" + lossPercent;
				}
			}
		}
		return str;
	}

	public static String getHotCoolData(String matchId) throws Exception {
		String str = "";
		String URL = DataUrls.ft_bifa + matchId;

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
		String URL = DataUrls.ft_bifa + matchId;

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
		String URL = DataUrls.ft_bifa + matchId;

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
		String URL = "https://smc.jdddata.com/api/betfair/getbfblocktradeinfo?&matchid=" + matchId;

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
	
	public static boolean checkRate(String homeWinRate, String awayWinRate){
		int home = Integer.valueOf(homeWinRate);
		int away = Integer.valueOf(awayWinRate);
		int value = home-away;
		value = Math.abs(value);
		if (home > 60 || away > 60 || home < 40 || away < 40 || (home + away) != 100) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		
	}

}
