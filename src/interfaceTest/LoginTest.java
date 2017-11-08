package interfaceTest;

import java.io.IOException;
import com.jdd.fm.core.exception.AesException;
import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import net.sf.json.JSONObject;

public class LoginTest {
	
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

		}
		
		obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		String token = data.getString("token");
		String userId = data.getString("id");
		
		UserInfo user = new UserInfo(name, pw, userId, token);
		return  user;
	}
	
	/**
	 * 设置100接口请求参数
	 * @param num：手机号
	 * @param pwd：密码
	 * @param cmdName：渠道号 
	 * @return
	 */
	public static String set100Param(String num, String pwd, String cmdName){
		String params = DataUrls.params_100;
		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		header.put("cmdName", cmdName);
		obj.put("header", header);
		
		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		body.put("Name", num);
		body.put("Pw", pwd);

		obj.put("body", body);
		params = obj.toString();
		return params;
	}
	
	/**
	 * 
	 * 设置100接口请求参数
	 * @param num：手机号
	 * @param pwd：密码
	 * @param cmdName：渠道号 
	 * @param uuid 设备ID
	 * @return
	 */
	public static String set100Param(String num, String pwd, String cmdName, String uuid){
		String params = DataUrls.params_100;
		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		header.put("cmdName", cmdName);
		header.put("uuid", uuid);
		obj.put("header", header);
		
		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		body.put("Name", num);
		body.put("Pw", pwd);

		obj.put("body", body);
		params = obj.toString();
		return params;
	}
	
	/**
	 * 
	 * 设置100接口(注册获取验证码)请求参数
	 * @param num：手机号
	 * @param pwd：密码
	 * @param cmdName：渠道code，app_zz、app_ios_zz、pc、h5_zz
	 * @param uuid 设备ID
	 * @param platformCode：IPHONE、Android、pc
	 * @return
	 */
	public static String set100Param(String num, String pwd, String cmdName, String uuid, String platformCode){
		String params = DataUrls.params_100;
		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		header.put("uuid", uuid);
		header.put("cmdName", cmdName);
		header.put("platformCode", platformCode);
		obj.put("header", header);
		
		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		body.put("Name", num);
		body.put("Pw", pwd);

		obj.put("body", body);
		params = obj.toString();
		return params;
	}
	
	/**
	 * 设置1034(注册校验验证码)接口参数
	 * @param num：手机号
	 * @param code：验证码
	 * @param cmdName：渠道code
	 * @return
	 */
	public static String set1034Param(String num, String code, String cmdName){
		String params = DataUrls.params_1034;
		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		header.put("cmdName", cmdName);
		obj.put("header", header);
		
		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		body.put("mobile", num);
		body.put("verifycode", code);

		obj.put("body", body);
		params = obj.toString();
		return params;
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
	public static boolean verifyCode(String num, String code, String cmdName) throws AesException, IOException{
		String params = DataUrls.params_1034;
		String url = DataUrls.user_url;
		String suc = "注册成功";
		
		params = set1034Param(num, code, cmdName);
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
		
		params = set100Param(num, pwd, cmdName);
		boolean result = false;
		String reString = AppReq.getResStr(url, params);
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, num);
			
			result = verifyCode(num, code, cmdName);
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
		
		params = set100Param(num, pwd, cmdName, uuid, platformCode);
		System.out.println(params);
		boolean result = false;
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, num);
			result = verifyCode(num, code, cmdName);
		}
		
		return result;
	}
	
	public static boolean getUserBaseInfo(String num, String pwd) throws AesException, IOException{
		String url = DataUrls.user_url;
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
	
//	public static boolean setUserIDCardNum(String num, String pwd, String RName, String IDCard) throws AesException, IOException{
//		String url = DataUrls.user_url;
//		String params = DataUrls.params_102;
//		String suc = "操作成功";
//		
//		UserInfo user = getUserInfo(num, pwd);
//		
//		String token = user.getToken();
//		String userId = user.getUserId();
//		
//		String hParams = "userID," + userId + ";token," + token;
//		String bParams = "RName,"+ RName + ";IDCard," + IDCard;
//		
//		params = AppReq.setParmas(params, hParams, bParams);
//		String reString = AppReq.getResStr(url, params);
//		
//		System.out.println(reString);
//		if (reString.contains(suc)) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	public static void main(String[] args) throws AesException, IOException {
		String pwd = "aaaaaa";
//		for (int i = 1; i < 10; i++) {
//			String num = "1301111000";
//			getUserBaseInfo(num+i, pwd);
//		}
//		
		for (int i = 0; i < 10; i++) {
			String num = "1301111002";
			getUserBaseInfo(num+i, pwd);
		}
		
//		String IDCard = "320721199110292827";
//		String RName = "王二";
//		
//		String cmdName = "app_ios_zz";
//		String type = "zz";
//		String uuid = "1C98D64157704EE8AE9070D06499A257";
//		String platformCode = "IPHONE";
		
//		registerUseCmdName(type, num, pwd, cmdName);
//		registerUseCmdName(type, num, pwd, cmdName, uuid, platformCode);
//		setUserIDCardNum(num, pwd, RName, IDCard);
	}
}
