package interfaceTest;

import java.io.IOException;
import com.jdd.fm.core.exception.AesException;
import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import net.sf.json.JSONObject;

public class UserBaseInfo {
	
	public static UserInfo getUserInfo(String name, String pw) throws AesException, IOException{
		String url = DataUrls.user_url;
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
	 * 校验验证码
	 * @param num
	 * @param code
	 * @param cmdName
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean verifySmsCode(String num, String code, String cmdName) throws AesException, IOException{
		String params = DataUrls.params_1034;
		String url = DataUrls.user_url;
		String suc = "注册成功";
		
		String hParams = "cmdName," + cmdName;
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
	public static boolean registerUseCmdName(String type, String num, String pwd, String cmdName) throws AesException, IOException{
		String params = DataUrls.params_100;
		String url = DataUrls.user_url;
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
	 * @param num
	 * @param pwd
	 * @param cmdName
	 * @param uuid
	 * @param platformCode
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean registerUseCmdName(String type, String num, String pwd, String cmdName, String uuid, String platformCode) throws AesException, IOException{
		String params = DataUrls.params_100;
		String url = DataUrls.user_url;
		String suc = "验证码发送成功";
		
		String hParams = "cmdName," + cmdName + ";uuid," + uuid + ";platformCode," + platformCode;
		String bParams = "Name," + num + ";Pw," + pwd;
		params = AppReq.setParmas(params, hParams, bParams);
		System.out.println(params);
		boolean result = false;
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, num);
			result = verifySmsCode(num, code, cmdName);
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
	public static boolean getUserBaseInfo(String num, String pwd) throws AesException, IOException{
		String url = DataUrls.user_url;
		String params = DataUrls.params_107;
		String suc = num;
		String uuid = "04FCEE6BDE0F461FACD85";
		
		UserInfo user = getUserInfo(num, pwd);
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String hParams = "userID," + userId + ";token," + token + ";uuid," + num + uuid;
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
	
	public static boolean setUserIDCardNum(String num, String pwd, String RName, String IDCard) throws AesException, IOException{
		String url = DataUrls.user_url;
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

	public static void main(String[] args) throws AesException, IOException {
		String pwd = "aaaaaa";
//		for (int i = 1; i < 10; i++) {
//			String mobile = "13898760002";
//			getUserBaseInfo(num+i, pwd);
//		}
//		
//		for (int i = 0; i < 10; i++) {
//			String num = "1301111002";
//			getUserBaseInfo(num+i, pwd);
//		}
		
//		String IDCard = "320721199110292827";
//		String RName = "王二";
		
//		String cmdName = "app_zz";
//		String type = "zz";
//		String uuid = mobile + "04FCEE6BDE0F461FACD85";
//		String platformCode = "IPHONE";
//		registerUseCmdName(type, mobile, pwd, cmdName, uuid, platformCode);
//		getUserBaseInfo(mobile, pwd);
		
		for (int i = 0; i < 1005; i++) {
			String mobile = "1342222";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if (i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			getUserBaseInfo(mobile, pwd);
		}
	}
}
