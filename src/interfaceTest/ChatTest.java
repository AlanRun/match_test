package interfaceTest;

import java.util.Date;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import net.sf.json.JSONObject;

public class ChatTest {
	


	static String url = "http://chat.jdd.com/webSocketServer.do";
	static String params = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdId\":0,\"platformVersion\":\"6.0\",\"cmdName\":\"app_zz\",\"userType\":1,\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"userID\":\"ODk5\",\"platformCode\":\"h5mobile\",\"token\":\"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJqc2NwIiwiaXNzIjoiamRkLmNvbSJ9.eyJ1c2VyVHlwZSI6MSwidXNlcmlkIjo4OTksInV1aWQiOiIzQzA3NTU1NU45TTAifQ.7a37c4913e1940192f3249cf3b5f10f5.NmMyYmI1MDEtNDNkZS00NmJjLWExYmItOTljYzgwYzJlYmIz\"},\"body\":\"{'matchId':'9291685'}\"}";
	
	public static void sendChat(UserInfo user) throws Exception{
		
		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		header.put("userID", user.getUserId());
		header.put("token", user.getToken());
		
		System.out.println(obj);
		
		String json = AppReq.getResStr(url, params);
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			System.out.println(json);
		}
		
		obj = JSONObject.fromObject(json);
	}
	
	/**
	 * 
	 * @param type 事件类型：
	 * 1	进球（领先1球)
	 * 2	进球（追回1球）
	 * 3	进球（比分追平）
	 * 4	进球（主队扩大优势）
	 * 24	乌龙球
	 * 25	乌龙球
	 * 27	进球（点球，比分领先）
	 * 28	进球（点球，比分追近）
	 * 29	进球（点球，比分追平）
	 * 30	进球（点球比分扩大）
	 * 34	进球（比分扳平）
	 * 35	进球（比分扩大）
	 * 36	进球（领先1球）
	 * 39	进球（点球，比分领先）
	 * 40	进球（点球，比分追进）
	 * 41	进球（点球，比分扳平）
	 * 42	进球（点球比分扩大）
	 * 43	乌龙球
	 * 58	点球球员得分
	 * 61	点球大战命中
	 * 129	进球（头球领先）
	 * 130	进球（头球领先）
	 * 131	进球（头球扳平）
	 * 132	进球（头球扳平）
	 * 133	进球（头球比分扩大）
	 * 134	进球（头球比分扩大）
	 * 135	进球（头球比分缩小）
	 * 136	进球（头球比分缩小）
	 * 137	进球（乌龙球）
	 * 138	进球（乌龙球）
	 * 139	助攻人（进球）
	 * @param tournamentStageId
	 * @throws Exception
	 */
	public static void sendMsg(String matchId, String type, String tournamentStageId, String uniqueTournamentId, String time, String tournamentType) throws Exception{
		String url = DataUrls.url_chat;
		String params = DataUrls.params_chat;
		String suc = "操作成功";

		String Params = "matchId," + matchId + ";type," + type + ";tournamentStageId," + tournamentStageId + ";textZh," + "type=" + type  + new Date().toString() + ";uniqueTournamentId," + uniqueTournamentId + ";time," + time + ";tournamentType," + tournamentType;
		params = "params=" + AppReq.setParmas(params, Params);
		System.out.println(params);
		String reString = AppReq.getResStrNotAes(url, params);
		System.out.println(reString);
	}
	
	public static void main(String[] args) throws Exception {
		String matchId = "13810373";
		String tournamentStageId = "1";
		String type = "1";
		String uniqueTournamentId = "16";
		String time = "1";
		String tournamentType = "C";
		sendMsg(matchId, type, tournamentStageId, uniqueTournamentId, time, tournamentType);
		
//		for (int i = 160; i < 167; i++) {
//			sendMsg(matchId, type, tournamentStageId, "" + i);
//			Thread.sleep(1000);
//		}
//		
//		for (int i = 0; i < 149; i++) {
//			sendMsg(matchId, ""+ i, tournamentStageId);
//			Thread.sleep(4000);
//		}
	}
}
