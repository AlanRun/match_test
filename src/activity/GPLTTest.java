package activity;

import java.io.IOException;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 高频擂台赛活动
 * 
 * @author jdd
 *
 */
public class GPLTTest {

	private static String actTypeId = "100008";
	private static String type = "zz";;

	/**
	 * 领取擂台资格
	 * 
	 * @param username
	 * @param id
	 * @param mobile
	 * @param newpwd
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean getActQualify7113(String name, String userID, String token) throws Exception {
		String params = DataUrls.params_7113;
		String url = DataUrls.url_act;
		String suc = "领取成功";

		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "actTypeId," + actTypeId;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			result = true;
		}
		return result;
	}

	/**
	 * 7111活动基本信息及绑定排名
	 * 
	 * @param mobile
	 * @param Multiple
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean get7111() throws Exception {
		String url = DataUrls.url_act;
		String params = DataUrls.params_7111;
		String suc = "code";

		String hParams = "";
		String bParams = "actTypeId," + actTypeId;

		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);

		if (reString.contains(suc)) {

			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			JSONArray today = data.getJSONArray("today");
			for (int i = 0; i < today.size(); i++) {
				JSONObject point = (JSONObject) today.get(i);
				String userName = point.getString("userName");
				System.out.println(userName);
			}

			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws Exception {

//		for (int i = 1; i < 100; i++) {
//			String mobile = "1341111";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if (i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
//			String token = user.getToken();
//			String userId = user.getUserId();
//			getActQualify7113(mobile, userId, token);
//		}

//		for (int i = 1; i < 45; i++) {
//			String mobile = "1341111";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if (i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			String LotteryID = "67";
//			if (i > 1000) {
//				UserBaseInfo.buyk3(mobile, 1000, LotteryID);
//			} else {
//				UserBaseInfo.buyk3(mobile, i, LotteryID);
//			}
//		}
		
		for (int i = 1; i < 100; i++) {
			String mobile = "1341111";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if (i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			String LotteryID = "74";
			if (i > 1000) {
				UserBaseInfo.buy11X5(mobile, 1000, LotteryID);
			} else {
				UserBaseInfo.buy11X5(mobile, i, LotteryID);
			}
		}

//		for (int i = 1; i < 40; i++) {
//			String mobile = "1341111";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if (i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
//			String token = user.getToken();
//			String userId = user.getUserId();
//
//			UserBaseInfo.getUserRedpackage(userId, token, "高频擂台");
//			System.err.println(mobile);
//		}
	}

}
