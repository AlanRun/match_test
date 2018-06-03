package activity;

import java.io.IOException;
import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;

/**
 * 大神跟单擂台赛活动
 * 
 * @author Alan
 *
 */
public class LYHHL3Test {

	private static String actTypeId = "NzczMTczLTc3MzE2OC1lbmM=";
//	private static String actTypeId = "NzczMTcyLTc3MzE3Ni1lbmM=";
	private static String pwd = "aaaaaa";
	private static String actName = "老用户回流三阶段";

	/**
	 * 领取三阶段资格
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
		String params = DataUrls.params_lyhhl3;
		String url = "https://act-api.jdd.com/api/v1/getActAckflowThreeGiftPacksInfo";
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
	
	public static boolean getActInfo() throws Exception {
		String params = DataUrls.params_lyhhl3_info;
		String url = "https://act-api.jdd.com/api/v1/getActAckflowThreeGiftPacksInfo";
		String suc = "success";
		
		String hParams = "";
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
	 * 领取三阶段资格
	 * 
	 * @param username
	 * @param id
	 * @param mobile
	 * @param newpwd
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean getActReward(String userID, String token) throws Exception {
		String params = DataUrls.params_lyhhl3_reward;
		String url = "https://act-api.jdd.com/api/v1/getGiftPacks";
		String suc = "大礼包已发放至您的账户";

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
	
	
	public static void init(int s, int e) throws Exception{
		for (int i = s; i < e; i++) {
			String mobile = "1350001";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if (i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, pwd);
//			String token = user.getToken();
//			String userID = user.getUserId();
//			UserBaseInfo.getMechartNo(userID, token, "2000");
//			getActQualify(userID, token);
		}
	}
	
	public static void getChance(int s, int e) throws Exception {
		for (int i = s; i < e; i++) {
			String mobile = "1350001";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if (i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			
			UserBaseInfo.buy11X5(mobile, 1, "62");
		}
	}
	
	public static void getReward(int s, int e) throws Exception {
		for (int i = s; i < e; i++) {
			String mobile = "1350001";
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
			
			getActReward(userID, token);
		}
	}
	
	public static void check(int s, int e) throws Exception {
		for (int i = s; i < e; i++) {
			String mobile = "1350001";
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
			
//			String win = UserBaseInfo.getFirstOrderMoney(userID, token);
//			System.err.println(win);
			UserBaseInfo.getUserRedpackage(userID, token,  actName);
		}
	}

	public static void main(String[] args) throws Exception {
		int s = 20;
		int e = 30;
		
//		init(s, e);
//		getChance(s, e);
		getActInfo();
		
		getReward(s, e);
		
		check(s, e);
		
//		for (int i = 0; i < 7; i++) {
//		}
	}
}
