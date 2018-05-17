package interfaceTest;

import java.io.IOException;
import com.jdd.fm.core.exception.AesException;
import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import net.sf.json.JSONObject;

public class LoginTest {
	
	public static UserInfo getUserInfo(String name, String pw) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_1011;

		JSONObject obj = JSONObject.fromObject(params);
		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		body.put("name", name);
		body.put("pw", pw);
		obj.put("body", body);
		
		params = obj.toString();
		
		String json = AppReq.getResStr(url, params);
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			System.out.println(json);
		}
		
		obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		String token = data.getString("token");
		String userId = data.getString("id");
		
		UserInfo user = new UserInfo(name, pw, userId, token);
		return  user;
	}

	/**
	 * 校验验证码
	 * @param num
	 * @param code
	 * @param cmdName
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean verifySmsCode(String num, String code, String cmdName) throws Exception{
		String params = DataUrls.params_1034;
		String url = DataUrls.url_user;
		String suc = "注册成功";
		
		String hParams = "cmdName," + cmdName + ";uuid," + num + "04EE8AE9070D06499A257";
		String bParams = "verifycode," + code + ";mobile," + num;
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
	 * 注册
	 * @param num 手机号
	 * @param pwd 密码
	 * @param cmdName 渠道名，如：app_zz、app_ios_zz、h5_zz
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean registerUseCmdName(String type, String num, String pwd, String cmdName) throws Exception{
		String params = DataUrls.params_100;
		String url = DataUrls.url_user;
		String suc = "验证码发送成功";
		
		String hParams = "cmdName," + cmdName;
		String bParams = "Name," + num + ";Pw," + pwd;
		params = AppReq.setParmas(params, hParams, bParams);
		boolean result = false;
		String reString = AppReq.getResStr(url, params);
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, num);
			
			result = verifySmsCode(num, code, cmdName);
		} else {
			System.out.println(reString);
		}
		
		System.out.println("register result =" + result);
		return result;
	}
	
	/**
	 * 
	 * @param type
	 * @param mobile
	 * @param pwd
	 * @param cmdName
	 * @param uuid
	 * @param platformCode
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean registerUseCmdName(String type, String mobile, String pwd, String cmdName, String uuid, String platformCode) throws Exception{
		String params = DataUrls.params_100;
		String url = DataUrls.url_user;
		String suc = "验证码发送成功";
		
		String hParams = "cmdName," + cmdName + ";uuid," + mobile + uuid + ";platformCode," + platformCode;
		String bParams = "Name," + mobile + ";Pw," + pwd;
		params = AppReq.setParmas(params, hParams, bParams);
		System.out.println(params);
		boolean result = false;
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, mobile);
			result = verifySmsCode(mobile, code, cmdName);
		}
		
		return result;
	}
	
	/**
	 * 获取用户基本信息
	 * @param num
	 * @param pwd
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean getUserBaseInfo(String num, String pwd) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_107;
		String suc = num;
		
		UserInfo user = getUserInfo(num, pwd);
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "";
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean setUserIDCardNum(String num, String pwd, String RName, String IDCard) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_102;
		String suc = "操作成功";
		
		UserInfo user = getUserInfo(num, pwd);
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "RName,"+ RName + ";IDCard," + IDCard;
		
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
//		for (int i = 1; i < 10; i++) {
//			String num = "13898760002";
//			getUserBaseInfo(num+i, pwd);
//		}
//		
//		for (int i = 0; i < 10; i++) {
//			String num = "1301111002";
//			getUserBaseInfo(num+i, pwd);
//		}
		
//		String IDCard = "320721199110292827";
//		String RName = "王二";
		String pwd = "aaaaaa";
		String cmdName = "app_zz";
		String type = "zz";
		String uuid = "57704EE8AE9070D06499A257";
		String platformCode = "IPHONE";
		
		for (int i = 100; i < 999; i++) {
			String mobile = "1350001";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
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
//			registerUseCmdName(type, mobile, pwd, cmdName, uuid, platformCode);
		}
	}
}
