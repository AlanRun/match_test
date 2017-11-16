package activity;

import java.io.IOException;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import interfaceTest.LoginTest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 竞足擂台赛活动
 * @author jdd
 *
 */
public class JZZBTest {
	
	/**
	 * 忘记密码重置密码
	 * @param type
	 * @param mobile
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean resetPwd(String type, String mobile) throws AesException, IOException{
		String params = DataUrls.params_1030;
		String url = DataUrls.user_url;
		
		String suc = "发送验证码成功";
		
		String hParams = "";
		String bParams = "mobile," + mobile;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			String id = data.getString("id"); 
			
			String verifyCode = SmsTest.getSmsCode(type, mobile);
			result = verify1032Code(id, mobile, verifyCode);
		}
		
		System.out.println("register result =" + result);
		return result;
	}
	
	/**
	 * 忘记密码验证码验证
	 * @param id
	 * @param mobile
	 * @param verifyCode
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean verify1032Code (String id, String mobile, String verifyCode) throws AesException, IOException{
		String params = DataUrls.params_1032;
		String url = DataUrls.user_url;
		String suc = "验证成功";
		String newpwd = "aaaaaa";
		
		String hParams = "";
		String bParams = "id," + id + ";verifycode," + verifyCode + ";username," + mobile;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			result = resetPwd1061(mobile, id, mobile, newpwd);
		}
		return result;
	}
	
	/**
	 * 忘记密码重置密码
	 * @param username
	 * @param id
	 * @param mobile
	 * @param newpwd
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean resetPwd1061(String username, String id, String mobile, String newpwd) throws AesException, IOException{
		String params = DataUrls.params_1061;
		String url = DataUrls.user_url;
		String suc = "修改密码成功";
		
		String hParams = "";
		String bParams = "username," + username + ";id," + id + ";mobile," + mobile+ ";newpwd," + newpwd;
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
	public static String getUserRedpackage(String userID, String token) throws AesException, IOException{
		String url = DataUrls.rp_url;
		String params = DataUrls.params_402;
		String suc = "code\":0";
		String result = "";
		
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
				String TotalMoney = item.getString("TotalMoney");
				if (TotalMoney.equals("3000")) {
					result = item.getString("SendID");
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 上报uuid
	 * @param userID
	 * @param token
	 * @param uuid
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean push1000(String userID, String token, String uuid) throws AesException, IOException{
		
		String url = DataUrls.push_url;
		String params = DataUrls.params_1000;
		String suc = "设置成功";
		boolean result = false;
		
		String hParams = "userID," + userID + ";token," + token + ";uuid," + uuid;
		String bParams = "";
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 购买竞足
	 * @param mobile
	 * @param Multiple
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean buyJZ(String mobile, int Multiple) throws AesException, IOException{
		String url = DataUrls.order_url;
		String params = DataUrls.params_207Jz;
		String suc = "操作成功";
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String HongBaoSelectID = getUserRedpackage(userId,token);
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "HongBaoSelectID," + HongBaoSelectID + ";Multiple," + Multiple + ";Money," + (Multiple*2);
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
//		String type = "zz";
//		String mobile = "13322221101";
//		buySSQ(mobile);
		
//		for (int i = 1100; i < 1102; i++) {
//			String mobile = "1332222";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			register7054Use(type, mobile, "D8AC6F436813D05E3F87841978B1299C", actTypeId);
//		}
//		
//		for (int i = 1100; i < 1102; i++) {
//			String mobile = "1332222";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			resetPwd(type, mobile);
//		}
		
//		for (int i = 0; i < 1005; i++) {
//			String mobile = "1332222";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
//			String token = user.getToken();
//			String userId = user.getUserId();
//			
//			push1000(userId, token, mobile + "04FCEE6BDE0F461FACD85");
//		}
		
		for (int i = 0; i < 1005; i++) {
			String mobile = "1332222";
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
				buyJZ(mobile, 1000);				
			} else {
				buyJZ(mobile, i);
			}
		}
		
	}

}
