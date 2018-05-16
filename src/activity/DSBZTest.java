package activity;

import java.io.IOException;
import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;

/**
 * 大神跟单包中活动
 * 
 * @author Alan
 *
 */
public class DSBZTest {

	private static String actTypeId = "46";
	private static String pwd = "aaaaaa";

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
	public static boolean getActQualify(String userID, String token) throws Exception {
		String params = DataUrls.params_act46;
		String url = "https://act-api.jdd.com/api/v1/receiveActVerify";
		String suc = "您成功领取该活动资格";

		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actId," + actTypeId;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			result = true;
		}
		return result;
	}
	
	public static void follow(int s, int e) throws Exception{
		for (int i = s; i < e; i++) {
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
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pwd);
			String token = user.getToken();
			String userID = user.getUserId();
			
			getActQualify(userID, token);
		}
		
		for (int i = s; i < e; i++) {
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
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pwd);
			String token = user.getToken();
			String userID = user.getUserId();
			
//			String schemeId = "87000070";
//			String recommendUserId = "891";
//			String schemeId = "87000074";
//			String recommendUserId = "899";
			String schemeId = "800698778";
			String recommendUserId = "1000011159";
			
			followOrder(userID, token, schemeId, recommendUserId);
		}
	}
	
	/**
	 * 跟单
	 * @param userID
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static boolean followOrder(String userID, String token) throws Exception {

		String params = DataUrls.params_followOrder;
		String url = "https://act-api.jdd.com/api/v1/buyGreatGodScheme";
		String suc = "success";

		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actId," + actTypeId;
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
	 * 跟单
	 * @param userID
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static boolean followOrder(String userID, String token, String schemeId, String recommendUserId) throws Exception {

		String params = DataUrls.params_followOrder;
		String url = "https://act-api.jdd.com/api/v1/buyGreatGodScheme";
		String suc = "success";

		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actId," + actTypeId + ";schemeId," + schemeId + ";recommendUserId," + recommendUserId;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			result = true;
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		int s = 43;
		int e = 44;
		
		follow(s, e);
	}
}
