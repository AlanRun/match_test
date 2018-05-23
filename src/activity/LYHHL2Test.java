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
public class LYHHL2Test {

	private static String actTypeId = "NzczMTcyLTc3MzE3Ny1lbmM=";
	private static String pwd = "aaaaaa";
	private static String actName = "老用户回流第二阶段";

	/**
	 * 领取二阶段资格
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
		String params = DataUrls.params_lyhhl2;
		String url = "http://act-api.baoying518.com/api/v1/getActAckflowTwoDoublesPrizeInfo";
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
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pwd);
			String token = user.getToken();
			String userID = user.getUserId();
			
			UserBaseInfo.getMechartNo(userID, token, "2000");
			getActQualify(userID, token);
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
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pwd);
			String token = user.getToken();
			String userID = user.getUserId();
			
			UserBaseInfo.buyJZ(userID, token, i%10 + 1);
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
			
			String win = UserBaseInfo.getFirstOrderMoney(userID, token);
//			System.err.println(win);
			UserBaseInfo.getUserRedpackage(userID, token,  actName);
		}
	}

	public static void main(String[] args) throws Exception {
		int s = 470;
		int e = 475;
		
		init(s, e);
		
		getChance(s, e);
		
//		check(s, e);
		
//		for (int i = 0; i < 7; i++) {
//		}
	}
}
