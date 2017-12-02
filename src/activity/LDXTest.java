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
 * 老带新活动
 * @author jdd
 *
 */
public class LDXTest {
	
	/**
	 * 老带新活动新用户注册
	 * @param type
	 * @param mobile
	 * @param userId
	 * @param actTypeId
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean register7054Use(String type, String mobile, String userId, String actTypeId) throws AesException, IOException{
		String params = DataUrls.params_20018;
		String url = DataUrls.url_user;
		String suc = "发送验证码成功";
		
		String hParams = "";
		String bParams = "mobile," + mobile;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			String verifyCode = SmsTest.getSmsCode(type, mobile);
			System.out.println(verifyCode);
			result = verify7054Code(actTypeId, userId, mobile, verifyCode);
		}
		
		System.out.println("register result =" + result);
		return result;
	}
	
	/**
	 * 7054接口验证
	 * @param actTypeId：活动typeID
	 * @param userId：老用户ID（加密）
	 * @param mobile：新用户手机号
	 * @param verifyCode：验证码
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean verify7054Code(String actTypeId, String userId, String mobile, String verifyCode) throws AesException, IOException{
		String params = DataUrls.params_7054;
		String url = DataUrls.url_act;
		String suc = "注册成功";
		
		String hParams = "";
		String bParams = "actTypeId," + actTypeId + ";userId," + userId + ";mobile," + mobile + ";verifyCode," + verifyCode;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 使用忘记重置密码
	 * @param type
	 * @param mobile
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean resetPwd(String type, String mobile) throws AesException, IOException{
		String params = DataUrls.params_1030;
		String url = DataUrls.url_user;
		
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
		String url = DataUrls.url_user;
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
	 * 修改密码
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
		String url = DataUrls.url_user;
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
	 * 获取用户总额为3000的红包ID
	 * @param userID
	 * @param token
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static String getUserRedpackage(String userID, String token) throws AesException, IOException{
		String url = DataUrls.url_rp;
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
	 * 上报用户uuid
	 * @param userID
	 * @param token
	 * @param uuid
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean push1000(String userID, String token, String uuid) throws AesException, IOException{
		
		String url = DataUrls.url_push;
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
	 * 使用红包购买双色球
	 * @param mobile
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean buySSQ(String mobile) throws AesException, IOException{
		String url = DataUrls.url_order;
		String params = DataUrls.params_207;
		String suc = "操作成功";
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String HongBaoSelectID = getUserRedpackage(userId,token);
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "HongBaoSelectID," + HongBaoSelectID;
		
		params = AppReq.setParmas(params, hParams, bParams);
		System.out.println(params);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		String type = "zz";
		String actTypeId = "48484";
		
		for (int i = 1100; i < 1102; i++) {
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
			register7054Use(type, mobile, "D8AC6F436813D05E3F87841978B1299C", actTypeId);
		}
		
		for (int i = 1100; i < 1102; i++) {
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
			resetPwd(type, mobile);
		}
		
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
			UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
			String token = user.getToken();
			String userId = user.getUserId();
			
			push1000(userId, token, mobile + "04FCEE6BDE0F461FACD85");
		}
		
		for (int i = 0; i < 42; i++) {
			String mobile = "133222208";
			if (i < 10) {
				mobile = mobile + "0" + i;
			} else {
				mobile = mobile + i;
			}
			buySSQ(mobile);
		}
	}

}
