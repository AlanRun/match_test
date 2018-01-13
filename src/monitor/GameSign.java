package monitor;

import activity.SDDZTest;
import helper.AppReq;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONObject;

public class GameSign {
	
	public static void gameResignAndGetGift(String token, String userID) throws Exception{
		
//		//APP登录，活动userID、token
//		UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
//		String token = user.getToken();
//		String userID = user.getUserId();
		
		// 登录游戏
		String url = "https://uic-api.beeplay123.com/uic/api/lottery/login";
		String params_login = "{\"token\":\"" + token +"\",\"userID\":\"" + userID +"\",\"userType\":1,\"appVersion\":\"5.1.8\",\"platformCode\":\"IPHONE\"}";
		
		String reString = AppReq.getResStrNotAes(url, params_login);
		System.out.println("login=" + reString);
		JSONObject ojb = JSONObject.fromObject(reString);
		String game_token = ojb.getString("data");
		
		//接受token，获取Authorization
		url = "https://uic-api.beeplay123.com/uic/api/user/login/accessToken";
		String params_access = "{\"token\":\""+ game_token +"\",\"type\":1}";
		reString = AppReq.getResStrNotAes(url, params_access);
		System.out.println("accessToken=" + reString);
		ojb = JSONObject.fromObject(reString);
		JSONObject data = ojb.getJSONObject("data");
		String Authorization = data.getString("accessToken");
		
//		url = "https://uic-api.beeplay123.com/uic/api/user/login/transInfo";
//		reString = AppReq.getResStrNotAes(url, "", Authorization);
//		System.out.println("transInfo=" + reString);
		
		url = "https://platform-api.beeplay123.com/wap/api/userchecklottery/lottery";
		reString = AppReq.getResStrNotAes(url, "", Authorization);
		System.out.println("userchecklottery=" + reString);
	}
	
	

	public static void main(String[] args) throws Exception {
		String mobiles = "13911112222,18911112222,18917171717,18913131313,18919191919,18926262626,18925252525,18929292929,18928282828,18701639930,18601950610";
		String pw = "aaaaaa";
		
		String[] mList = mobiles.split(",");
		for (int i = 0; i < mList.length; i++) {
			String mobile = mList[i];
			
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			
			UserBaseInfo.getUserRedpackage(userID, token);
			
			UserBaseInfo.sign20004(userID, token, mobile);
			UserBaseInfo.drawLott7103(userID, token, mobile);
			UserBaseInfo.drawLott7101(userID, token, mobile);
			
			gameResignAndGetGift(token, userID);
			
//			SDDZTest.get7067(mobile, userID, token);
//			SDDZTest.get7045(mobile, userID, token);
			
			Thread.sleep(2000);
		}
		
	}
}
