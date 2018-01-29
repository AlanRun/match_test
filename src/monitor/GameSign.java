package monitor;

import helper.AppReq;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameSign {
	
	public static void gameResignAndGetGift(String token, String userID) throws Exception{
		
		// 登录游戏
		String url_login = "https://uic-api.beeplay123.com/uic/api/lottery/login";
		String params_login = "{\"token\":\"" + token +"\",\"userID\":\"" + userID +"\",\"userType\":1,\"appVersion\":\"5.1.8\",\"platformCode\":\"IPHONE\"}";
		
		String re_login = AppReq.getResStrNotAes(url_login, params_login);
		System.out.println("login=" + re_login);
		JSONObject obj_login = JSONObject.fromObject(re_login);
		String game_token = obj_login.getString("data");
		
		//接受token，获取Authorization
		String url_access = "https://uic-api.beeplay123.com/uic/api/user/login/accessToken";
		String params_access = "{\"token\":\""+ game_token +"\",\"type\":1}";
		String re_access = AppReq.getResStrNotAes(url_access, params_access);
		System.out.println("accessToken=" + re_access);
		JSONObject obj_access = JSONObject.fromObject(re_access);
		JSONObject data_access = obj_access.getJSONObject("data");
		String Authorization = data_access.getString("accessToken");
		
//		String url_trans = "https://uic-api.beeplay123.com/uic/api/user/login/transInfo";
//		String re_trans = AppReq.getResStrNotAes(url_trans, "", Authorization);
//		System.out.println("transInfo=" + re_trans);
		
		//签到
		String url_sign = "https://platform-api.beeplay123.com/wap/api/userchecklottery/lottery";
		String re_sign = AppReq.getResStrNotAes(url_sign, "", Authorization);
		System.out.println("userchecklottery=" + re_sign);
		
		//获取奖励列表
		String url_check = "https://platform-api.beeplay123.com/wap/api/userchecklottery/checkawardlist";
		String re_check = AppReq.getResStrNotAes(url_check, "", Authorization);
		System.out.println("before sign === " + re_check);
//		//获取奖励列表
//		re_check = AppReq.getResStrNotAes(url_check, "", Authorization);
//		System.out.println("after sign === " + re_check);
		JSONObject obj_check = JSONObject.fromObject(re_check);
		JSONObject data_check = obj_check.getJSONObject("data");
		JSONArray checkAwardList = data_check.getJSONArray("checkAwardList");
		String isReceive = "";
		for (int i = 0; i < checkAwardList.size(); i++) {
			JSONObject award = (JSONObject) checkAwardList.get(i);
			if (i != 0) {
				isReceive = isReceive + ";";
			}
			isReceive = isReceive +award.getString("activityDay") + "," + award.getString("isReceive");
		}
		System.out.println(isReceive);
		
		String url_award = "https://platform-api.beeplay123.com/wap/api/userchecklottery/receivecheckaward";
		
		if (!isReceive.equals("")) {
			String[] P = isReceive.split(";");
			for (int i = 0; i < P.length; i++) {
				String params_award = "{\"checkDay\":";
				String param = P[i];
				String[] p = param.split(",");
				if (p[1].equals("4")) {
					params_award = params_award + p[0] + "}";
				} else {
					continue;
				}
				System.out.println(params_award);
				String re_award = AppReq.getResStrNotAes(url_award, params_award, Authorization);
				System.out.println(re_award);
			}
		}
	}
	

	public static void main(String[] args) throws Exception {
		String mobiles = "13911112222,18911112222,18917171717,18913131313,18919191919,18921212121,18924242424,18926262626,18925252525,18929292929,18928282828,18701639930,18601950610";
		String pw = "aaaaaa";
		
		String[] mList = mobiles.split(",");
		for (int i = 0; i < mList.length; i++) {
			String mobile = mList[i];
			
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			
			UserBaseInfo.push1000(userID, token);
			
//			UserBaseInfo.getUserRedpackage(userID, token);
			
			UserBaseInfo.sign20004(userID, token, mobile);
			UserBaseInfo.drawLott7103(userID, token, mobile);
			UserBaseInfo.drawLott7101(userID, token, mobile);
			
			gameResignAndGetGift(token, userID);
			
//			SDDZTest.get7067(mobile, userID, token);
//			SDDZTest.get7045(mobile, userID, token);
//			
//			Thread.sleep(2000);
		}
		
	}
}
