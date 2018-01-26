package activity;

import java.io.IOException;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 竞足擂台赛活动
 * @author Alan
 *
 */
public class JZLTTest {
	
	private static String actTypeId = "100001";
	private static String type = "zz";;
	
	/**
	 * 领取擂台资格
	 * @param username
	 * @param id
	 * @param mobile
	 * @param newpwd
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean getActQualify(String name, String userID, String token) throws AesException, IOException{
		String params = DataUrls.params_7055;
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
	 * 获取用户总额为3000的彩金卡ID
	 * @param userID
	 * @param token
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static void getUserRedpackage(String userID, String token, String act) throws AesException, IOException{
		String url = DataUrls.url_rp;
		String params = DataUrls.params_402;
		String suc = "code\":0";
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "";
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			JSONArray items = data.getJSONArray("item");
			for (int i = 0; i < items.size(); i++) {
				JSONObject item = (JSONObject) items.get(i);
				String Name = item.getString("Name");
				if (Name.equals(act)) {
					String TotalMoney = item.getString("TotalMoney");
					System.err.println(Name + " : " + TotalMoney);
				}
			}
		}
	}
	
	/**
	 * 7056活动基本信息及绑定排名
	 * @param mobile
	 * @param Multiple
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean get7056() throws AesException, IOException{
		String url = DataUrls.url_act;
		String params = DataUrls.params_7056;
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
		for (int i = 1; i < 50; i++) {
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
			UserBaseInfo.register7054Use(type, mobile, "D8AC6F436813D05E3F87841978B1299C");
		}
		
		for (int i = 1; i < 50; i++) {
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
			UserBaseInfo.resetPwd(type, mobile);
		}
		
		for (int i = 1; i < 50; i++) {
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
			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
			String token = user.getToken();
			String userId = user.getUserId();
			
			UserBaseInfo.push1000(userId, token, mobile + "04FCEE6BDE0F461FACD85");
		}
		
		for (int i = 1; i < 15; i++) {
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

			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
			String token = user.getToken();
			String userId = user.getUserId();
			getActQualify(mobile, userId, token);
		}
		
		for (int i = 1; i < 15; i++) {
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
			if (i > 1000) {
				UserBaseInfo.buyJZ(mobile, 1000);
			} else {
				UserBaseInfo.buyJZ(mobile, i);
			}
		}
//		
//		for (int i = 1; i < 15; i++) {
//		String mobile = "1341111";
//		if (i < 10) {
//			mobile = mobile + "000" + i;
//		} else if ( i < 100) {
//			mobile = mobile + "00" + i;
//		} else if (i < 1000) {
//			mobile = mobile + "0" + i;
//		} else if (i < 10000) {
//			mobile = mobile + i;
//		}
//		UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
//		String token = user.getToken();
//		String userId = user.getUserId();
//		
//		getUserRedpackage(userId, token, "足球擂台赛");
//		System.err.println(mobile);
//		}
	}

}
