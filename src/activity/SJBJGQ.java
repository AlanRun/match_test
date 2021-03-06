package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONObject;
import utils.LogWrite;

/**
 * world cup collect flag
 * @author Alan
 *
 */
public class SJBJGQ {
	
	private static String pw = "aaaaaa";
	private static String actTypeId = "100020";

	public static int getFlagCount(String userID, String token) throws Exception{
		String params = DataUrls.params_8007;
		String url = DataUrls.url_act;
		String suc = "code\":1";
		int unusedDrawTime = 0;
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actTypeId," + actTypeId ;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		JSONObject obj = JSONObject.fromObject(reString);
		JSONObject data = obj.getJSONObject("data");
		unusedDrawTime= data.getInt("unusedDrawTime");
		return unusedDrawTime;
	}
	
	public static void useFlagCount(String userID, String token) throws Exception{
		String params = DataUrls.params_8009;
//		String params = DataUrls.params_8013;
		String url = DataUrls.url_act;
		String suc = "抽奖成功";
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actTypeId," + actTypeId ;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		if (reString.contains(suc)) {
			System.out.println(reString);
		} else {
			System.err.println(reString);
		}
	}
	
	public static void useFlagCount(String userID, String token, String actTypeId) throws Exception{
		String params = DataUrls.params_8009;
		String url = DataUrls.url_act;
		String suc = "抽奖成功";
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actTypeId," + actTypeId ;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		if (reString.contains(suc)) {
			System.out.println(reString);
		} else {
			System.err.println(reString);
		}
	}
	
	public static int getFlagNum(String mobile, String userID, String token) throws Exception{
		String params = DataUrls.params_8011;
		String url = DataUrls.url_act;
		String suc = "code\":1";
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actTypeId," + actTypeId ;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		int numFlag = 0;
		int mySet = 0;
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			numFlag = data.getInt("numFlag");
			mySet = data.getInt("mySet");
			System.err.println(mobile + "国旗套数=" + mySet);
			System.err.println(mobile + "国旗总数=" + numFlag );
		}
		return numFlag;
	}
	
	public static int getFlagNum(String mobile, String userID, String token, String actTypeId) throws Exception{
		String params = DataUrls.params_8011;
		String url = DataUrls.url_act;
		String suc = "code\":1";
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actTypeId," + actTypeId ;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		int numFlag = 0;
		int mySet = 0;
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			numFlag = data.getInt("numFlag");
			mySet = data.getInt("mySet");
			System.err.println(mobile + "国旗套数=" + mySet);
			System.err.println(mobile + "国旗总数=" + numFlag );
		}
		return numFlag;
	}

	public static void test() throws Exception{
		int s = 1;
		int e = 51;
		for (int i = s; i < e; i++) {
			String mobile = "1341111";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			
			UserBaseInfo.buyJZ(userID, token, 5000);
		}
		
//		for (int i = s; i < e; i++) {
//			String mobile = "1341111";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
//			String token = user.getToken();
//			String userID = user.getUserId();
//			int count = getFlagCount(userID, token);
//			System.err.println(mobile + "可抽次数=" + count);
//			getFlagNum(mobile, userID, token);
//		}
//		
//		for (int j = 0; j < 1; j++) {
//			for (int i = s; i < e; i++) {
//				String mobile = "1341111";
//				if (i < 10) {
//					mobile = mobile + "000" + i;
//				} else if ( i < 100) {
//					mobile = mobile + "00" + i;
//				} else if (i < 1000) {
//					mobile = mobile + "0" + i;
//				} else if (i < 10000) {
//					mobile = mobile + i;
//				}
//				UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
//				String token = user.getToken();
//				String userID = user.getUserId();
//				
//				useFlagCount(userID, token);
//				int count = getFlagCount(userID, token);
////				count = getFlagCount(userID, token);
//				System.err.println(mobile + "可抽次数=" + count);
////				LogWrite.saveToFile(mobile + "可抽次数=" + count);
//			}
//		}
		
//		for (int i = s; i < e; i++) {
//			String mobile = "1341111";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw );
//			String token = user.getToken();
//			String userID = user.getUserId();
//			
//			int count = getFlagCount(userID, token);
//			for (int j = 0; j < count;) {
//				useFlagCount(userID, token);
//				Thread.sleep(800);
//				count = getFlagCount(userID, token);
//				System.err.println(mobile + "可抽次数=" + count);
//				LogWrite.saveToFile(mobile + "可抽次数=" + count);
//			}
//		}
//		
//		for (int i = s; i < e; i++) {
//			String mobile = "1341111";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw );
//			String token = user.getToken();
//			String userID = user.getUserId();
//			
//			getFlagNum(mobile, userID, token);
//		}
	}
	
	public static void main(String[] args) throws Exception {
		test();
	}
}
