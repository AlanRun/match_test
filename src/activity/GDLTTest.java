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
public class GDLTTest {

	private static String actTypeId = "39";
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
		String params = DataUrls.params_act39;
		String url = "https://act-api.jdd.com/api/v1/createPeriodVerify";
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
			
//			UserBaseInfo.followOrder(userID, token, i);
			UserBaseInfo.followOrder(userID, token, "87000147", i);
		}
	}

	public static void main(String[] args) throws Exception {
		int s = 1;
		int e = 40;
		
		follow(s, e);
	}

}
