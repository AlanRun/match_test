package datePush;

import java.io.IOException;

import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class TeamRank {
	private static String url = DataUrls.ft_teamrank;

	public static String getTeamRank(String mid) throws IOException {
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
					NMm = NMm + ",\t" + groupName + "  " + rankingNum;
					break;
				}
			}
		}

		System.out.println(NMm);
		return NMm;
	}

	public static void main(String[] args) throws IOException {
		getTeamRank("12330696");
	}

}
