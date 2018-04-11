package interfaceTest;

import java.io.IOException;
import com.jdd.fm.core.exception.AesException;

import activity.Test107;
//import activity.Test108;
import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserBaseInfo {
	
//	private String mobile;
//	private String pw;
//	
//	public UserBaseInfo (String userName, String password){
//		pw = password;
//		mobile = userName;
//	}
	
	public static UserInfo getUserInfo(String name, String pw) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_1011;

		String hParams = "uuid," + name + "04FCEE6BDE0F461FACD85";
		String bParams = "name," + name + ";pw," + pw;
		params = AppReq.setParmas(params, hParams, bParams);
		
		String reString = AppReq.getResStr(url, params);
//		System.out.println(reString);
		
		JSONObject obj = JSONObject.fromObject(reString);
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
	 * 获取用户总额为3000的彩金卡ID
	 * @param userID
	 * @param token
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static String getUserRedpackage(String userID, String token) throws Exception{
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
				if (TotalMoney.contains("9000") ) {
					result = item.getString("SendID");
				}
			}
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
	public static boolean buySSQ(String mobile) throws Exception{
		String url = DataUrls.url_order;
		String params = DataUrls.params_207;
		String suc = "操作成功";
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "";
		
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
	
	
	/**
	 * 
	 * @param LottID
	 * 29 吉林时时彩
	 * 62 老11选五
	 * 67 快3
	 * 68 新快3
	 * 69 吉林快3
	 * 71 广西11选5
	 * 72 幸运11选5
	 * 73 新11选5
	 * 74 欢乐11选5
	 * 75 江西11选5
	 * 78 好运11选5
	 * 79 青海11选5
	 * 81 重庆快乐十分
	 * @return
	 * @throws Exception
	 */
	public static String getIssueName(String LottID) throws Exception {
		String url = DataUrls.url_bd;
		String params = DataUrls.params_200;
		String suc = "code\":0";
		
		String IssueName = "";
		
		String hParams = "";
		String bParams = "LottID," + LottID;
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONArray data = obj.getJSONArray("data");
			
			if (!data.toString().equals("") && !data.toString().equals("[]")) {
				
				JSONObject d = (JSONObject) data.get(0);
				IssueName = d.getString("IssueName");
			}
		}
		
		return IssueName;
	}
	
	/**
	 * 购买11选5
	 * @param mobile
	 * @param Multiple
	 * @return
	 * @throws Exception 
	 */
	public static boolean buy11X5(String mobile, int Multiple, String LotteryID) throws Exception{
		String params = DataUrls.params_207_11x5;
		String url = DataUrls.url_order;
		String suc = "操作成功";
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String IssueName = getIssueName(LotteryID);
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "Multiple," + Multiple + ";Money," + (Multiple*2) + ";LotteryID," + LotteryID + ";IssueName," + IssueName;
		String nParams = "playid," + LotteryID + "10";
		
		params = AppReq.setParmas(params, hParams, bParams, nParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 购买高频
	 * 29 吉林时时彩
	 * 62 老11选五
	 * 67 快3
	 * 68 新快3
	 * 69 吉林快3
	 * 71 广西11选5
	 * 72 幸运11选5
	 * 73 新11选5
	 * 74 欢乐11选5
	 * 75 江西11选5
	 * 78 好运11选5
	 * 79 青海11选5
	 * 81 重庆快乐十分
	 * @param mobile 用户名
	 * @param Multiple 倍数
	 * @param LotteryID 彩种ID
	 * @return
	 * @throws Exception 
	 */
	public static boolean buyGaoPin(String mobile, int Multiple, String LotteryID) throws Exception{
		String params = "";
		String url = DataUrls.url_order;
		String suc = "操作成功";
		String nParams = "";
		
		if (LotteryID.equals("62") || LotteryID.equals("71") || LotteryID.equals("72") || LotteryID.equals("78") || LotteryID.equals("74")) {
			params = DataUrls.params_207_11x5;
			nParams = "playid," + LotteryID + "10";
		} else if (LotteryID.equals("67") || LotteryID.equals("68") || LotteryID.equals("69")) {
			params = DataUrls.params_207_k3;
			nParams = "playid," + LotteryID + "05";
		}
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String IssueName = getIssueName(LotteryID);
		
		if (IssueName.equals("")) {
			return false;
		}
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "Multiple," + Multiple + ";Money," + (Multiple*2) + ";LotteryID," + LotteryID + ";IssueName," + IssueName;
		
		params = AppReq.setParmas(params, hParams, bParams, nParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 108接口调起充值
	 * 
	 * @param userID
	 * @param token
	 * @throws Exception
	 */
	public static void getMechartNo(String userID, String token, String money) throws Exception {
		String url = DataUrls.url_trade;
		String params = DataUrls.params_108;
		String suc = "code\":0";

		String hParams = "userID," + userID + ";token," + token;
		String bParams = "PayMoney," + money;
		params = AppReq.setParmas(params, hParams, bParams);
		System.out.println(params);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			String MechartNo = data.getString("MechartNo");

			String re = AppReq.getResStrByGet(DataUrls.url_pay + "payNumber=" + MechartNo + "&tradeMoney=" + money);
			System.out.println(re);
		}
	}
	
	/**
	 * 购买高频
	 * 29 吉林时时彩
	 * 62 老11选五
	 * 67 快3
	 * 68 新快3
	 * 69 吉林快3
	 * 71 广西11选5
	 * 72 幸运11选5
	 * 73 新11选5
	 * 74 欢乐11选5
	 * 75 江西11选5
	 * 78 好运11选5
	 * 79 青海11选5
	 * 81 重庆快乐十分
	 * @param mobile 用户名
	 * @param Multiple 倍数
	 * @param LotteryID 彩种ID
	 * @return
	 * @throws Exception 
	 */
	public static boolean buyGaoPin(String userID, String token, int Multiple, String LotteryID) throws Exception{
		String params = "";
		String url = DataUrls.url_order;
		String suc = "操作成功";
		String nParams = "";
		
		if (LotteryID.equals("62") || LotteryID.equals("71") || LotteryID.equals("72") || LotteryID.equals("78") || LotteryID.equals("74")) {
			params = DataUrls.params_207_11x5;
			nParams = "playid," + LotteryID + "10";
		} else if (LotteryID.equals("67") || LotteryID.equals("68") || LotteryID.equals("69")) {
			params = DataUrls.params_207_k3;
			nParams = "playid," + LotteryID + "05";
		}
		
		String IssueName = getIssueName(LotteryID);
		
		if (IssueName.equals("")) {
			return false;
		}
		
		String rp = getUserRedpackage(userID, token);
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "Multiple," + Multiple + ";Money," + (Multiple*2) + ";LotteryID," + LotteryID + ";IssueName," + IssueName + ";HongBaoSelectID," + rp;
		
		params = AppReq.setParmas(params, hParams, bParams, nParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 购买快3
	 * @param mobile
	 * @param Multiple
	 * @return
	 * @throws Exception 
	 */
	public static boolean buyk3(String mobile, int Multiple, String LotteryID) throws Exception{
		String params = DataUrls.params_207_k3;
		String url = DataUrls.url_order;
		String suc = "操作成功";
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String IssueName = getIssueName(LotteryID);
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "Multiple," + Multiple + ";Money," + (Multiple*2) + ";LotteryID," + LotteryID + ";IssueName," + IssueName;
		String nParams = "playid," + LotteryID + "05";
		
		params = AppReq.setParmas(params, hParams, bParams, nParams);
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 购买竞足
	 * @param mobile 用户名
	 * @param Multiple 倍数
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean buyJZ(String mobile, int Multiple) throws Exception{
		String url = DataUrls.url_order;
		String params = DataUrls.params_207Jz;
		String suc = "操作成功";
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "Multiple," + Multiple + ";Money," + (Multiple*2);
		
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
	 * 
	 * @param userId
	 * @param token
	 * @param Multiple
	 * @return
	 * @throws Exception
	 */
	public static boolean buyJZ(String userId, String token, int Multiple) throws Exception{
		String url = DataUrls.url_order;
		String params = DataUrls.params_207Jz;
		String suc = "操作成功";
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "Multiple," + Multiple + ";Money," + (Multiple*2);
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		
		System.err.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 购买竞篮
	 * @param mobile 用户名
	 * @param Multiple 倍数
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean buyJL(String mobile, int Multiple) throws Exception{
		String url = DataUrls.url_order;
		String params = DataUrls.params_207JL;
		String suc = "操作成功";
		
		UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
		
		String token = user.getToken();
		String userId = user.getUserId();
		
		String hParams = "userID," + userId + ";token," + token;
		String bParams = "Multiple," + Multiple + ";Money," + (Multiple*2);
		
		System.out.println(hParams);
		System.out.println(bParams);
		
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
//		String url = DataUrls.url_user;
		String url = DataUrls.url_user_ay;
		String suc = "验证码发送成功";
		String uuid = "04FCEE6BDE0F461FACD85";
		
		String hParams = "cmdName," + cmdName + ";uuid," + num + uuid;
		String bParams = "Name," + num + ";Pw," + pwd;
		params = AppReq.setParmas(params, hParams, bParams);
		boolean result = false;
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, num);
			
			result = verifySmsCode(num, code, cmdName);
		} else {
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
	public static boolean registerUseCmdName(String type, String num, String pwd, String cmdName, String uuid, String platformCode) throws Exception{
		String params = DataUrls.params_100;
		String url = DataUrls.url_user;
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
	
	public static void getRpNum107(String mobile, String userID, String token) throws Exception{
		String params = DataUrls.params_107;
		String url = DataUrls.url_user;
		String suc = "code\":0";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + mobile;
		String bParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
//		if (reString.contains(suc)) {
//			JSONObject obj = JSONObject.fromObject(reString);
//			JSONObject data = obj.getJSONObject("data");
//			int UseableCount = data.getInt("UseableCount");
//			
//			for (int i = 0; i < 30; i++) {
//				if (UseableCount < 2) {
//					System.err.println("107 rp num : " + UseableCount);
//				} else {
//					System.out.println("107 rp num : " + UseableCount);
//				}
//			}
//		}
	}
	
	/**
	 * 获取用户基本信息
	 * @param num
	 * @param pw
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean getUserBaseInfo(String num, String pw) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_107;
		String suc = num;
		String uuid = "04FCEE6BDE0F461FACD85";
		
		UserInfo user = getUserInfo(num, pw);
		
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
	
	/**
	 * 签到
	 * @param userID
	 * @param token
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public static boolean sign20004(String userID, String token, String num) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_20004;
		String suc = "操作成功";
		String uuid = "04FCEE6BDE0F461FACD85";
		
		String hParams = "userID," + userID + ";token," + token + ";uuid," + num + uuid;
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
	
	/**
	 * @param userID
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static boolean check10720(String userID, String token) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_20004;
		String suc = "操作成功";
		String uuid = "04FCEE6BDE0F461FACD85";
		
		String hParams = "userID," + userID + ";token," + token + ";uuid," + uuid;
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
	
	/**
	 * @param userID
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static boolean check1051(String userID, String token) throws Exception{
		String url = DataUrls.url_user;
		String params = DataUrls.params_20004;
		String suc = "操作成功";
		String uuid = "04FCEE6BDE0F461FACD85";
		
		String hParams = "userID," + userID + ";token," + token + ";uuid," + uuid;
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
	
	/**
	 * 大转盘抽奖
	 * @param userID
	 * @param token
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public static boolean drawLott7101(String userID, String token, String mobile) throws Exception{
		String url = DataUrls.url_act;
		String params = DataUrls.params_7101;
		String suc = "抽奖成功";
		String uuid = "04FCEE6BDE0F461FACD85";
		
		String hParams = "userID," + userID + ";token," + token + ";uuid," + mobile + uuid;
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
	/**
	 * 大转盘抽奖
	 * @param userID
	 * @param token
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public static boolean drawLott7103(String userID, String token, String mobile) throws Exception{
		String url = DataUrls.url_act;
		String params = DataUrls.params_7103;
		String suc = "抽奖成功";
		String uuid = "04FCEE6BDE0F461FACD85";
		
		String hParams = "userID," + userID + ";token," + token + ";uuid," + mobile + uuid;
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
	
	
	public static boolean register100UseVoice(String type, String mobile, String cmdName) throws Exception{
		String params = DataUrls.params_100_v;
		String url = DataUrls.url_user;
		String suc = "验证码发送成功";
		String uuid = "C4A57AD8DE42C3EB7C83B";
		
		String hParams = "uuid," + mobile + uuid;
		String bParams = "Name," + mobile;
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, mobile);
			System.out.println(code);
			result = verifySmsCode(mobile, code, cmdName);
		}
		
		System.out.println("register result =" + result);
		return result;
	}
	
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
	public static boolean register7054Use(String type, String mobile, String userId) throws Exception{
		String params = DataUrls.params_20018;
		String url = DataUrls.url_user;
		String suc = "发送验证码成功";
		String actTypeId = "48484";
		String uuid = "C4A57AD8DE42C3EB7C83B";
		
		String hParams = "uuid," + mobile + uuid;
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
	 * 老带新活动新用户注册
	 * @param type
	 * @param mobile
	 * @param userId
	 * @param actTypeId
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean register7054Use(String type, String mobile, String userId, String actTypeId) throws Exception{
		String params = DataUrls.params_20018;
		String url = DataUrls.url_user;
		String suc = "发送验证码成功";
		String uuid = "C4A57AD8DE42C3EB7C83B";
		
		String hParams = "uuid," + mobile + uuid;
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
	public static boolean verify7054Code(String actTypeId, String userId, String mobile, String verifyCode) throws Exception{
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
	 * 忘记密码重置密码
	 * @param type
	 * @param mobile
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean resetPwd(String type, String mobile) throws Exception{
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
	public static boolean verify1032Code (String id, String mobile, String verifyCode) throws Exception{
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
	 * 口令红包兑换彩金卡
	 * @param id
	 * @param mobile
	 * @param verifyCode
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean getRp8341 (String userID, String token) throws Exception{
		String params = DataUrls.params_8341;
		String url = DataUrls.url_act;
		String suc = "获取成功";
		boolean result = false;
		
		String hParams = "userID," + userID + ";token," + token;
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
	 * 忘记密码重置密码
	 * @param username
	 * @param id
	 * @param mobile
	 * @param newpwd
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean resetPwd1061(String username, String id, String mobile, String newpwd) throws Exception{
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
	 * 获取用户对于活动彩金卡
	 * @param userID
	 * @param token
	 * @param act
	 * @throws Exception
	 */
	public static void getUserRedpackage(String userID, String token, String act) throws Exception{
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
	 * 获取用户对于活动彩金卡
	 * @param userID
	 * @param token
	 * @param act
	 * @throws Exception
	 */
	public static void getUserRedpackage(String userID, String token, String act, String addTime) throws Exception{
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
				String AddedTime = item.getString("AddedTime");
				if (Name.equals(act) && AddedTime.contains(addTime)) {
					String TotalMoney = item.getString("TotalMoney");
					System.err.println(Name + " : " + TotalMoney);
				}
			}
		}
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
	public static boolean push1000(String userID, String token, String uuid) throws Exception{
		
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
	 * 上报uuid
	 * @param userID
	 * @param token
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean push1000(String userID, String token) throws Exception{
		
		String url = DataUrls.url_push;
		String params = DataUrls.params_1000;
		String suc = "设置成功";
		boolean result = false;
		
		String uuid = token.substring(token.length()-32, token.length());
//		String uuid = "112233445566";
//		System.out.println(uuid);
		
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
	
	public static boolean test100() throws Exception{
		
		String url = DataUrls.url_user;
		String params = DataUrls.params_100_v;
		String suc = "验证码发送成功";
		boolean result = false;
		
		String mobile = "13411120006";
		String type = "zz";
		String cmdName = "app_zz";
		
		String hParams = "";
		String bParams = "Name," + mobile;
		
		params = AppReq.setParmas(params, hParams, bParams);
		
		System.err.println(params);
		
		String reString = AppReq.getResStr(url, params);
		
		System.out.println(reString);
		if (reString.contains(suc)) {
			String code = SmsTest.getSmsCode(type, mobile);
			result = verifySmsCode(mobile, code, cmdName);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
//		int Multiple = 1;
		String mobile = "13811110030";
		String pw = "aaaaaa";
//		buy11X5(mobile, Multiple, "62");
//		buyk3(mobile, Multiple, "69");
		
//		test100();
//		UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
//		String token = user.getToken();
//		String userID = user.getUserId();
		String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJqc2NwIiwiaXNzIjoiamRkLmNvbSJ9.eyJ1c2VyVHlwZSI6MSwidXNlcmlkIjoxMDAwMDIzOTc3LCJ1dWlkIjoiMTM0MTExNDAwMDUwNEZDRUU2QkRFMEY0NjFGQUNEODUifQ.7a37c4913e1940192f3249cf3b5f10f5.ODhlY2Q2ZWEtYjY5Ni00OGI5LWE3ZTQtYTcwMTQ4NzExNWNi";
		String userID = "MTAwMDAyMzk3Nw==";
//		for (int i = 0; i < 30; i++) {
			getRp8341(userID, token);
//		}
		
//		String type = "zz";
//		for (int i = 1; i < 10000; i++) {
//			String mobile = "1341111";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserBaseInfo.register7054Use(type, mobile, "D8AC6F436813D05E3F87841978B1299C");
//		}
//		UserBaseInfo.register7054Use(type, "13411111465", "D8AC6F436813D05E3F87841978B1299C");
//		UserBaseInfo.resetPwd(type, "13411111465");
//		
	}
}
