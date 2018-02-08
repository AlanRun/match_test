package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONObject;

/**
 * 世界杯主力俄罗斯
 * @author Alan
 *
 */
public class SJBZL {
	
	private static String type = "zz";
	private static String actTypeId = "111926";
	private static String userId = "9C0F803EBC0D6BC443F1103A9B3B1CCE";
	private static String pw = "aaaaaa";
	
	public static boolean  regUseBy8002(String mobile, String verifyCode) throws Exception {
		String params = DataUrls.params_8002;
		String url = DataUrls.url_act;
		String suc = "注册成功";
		
		String hParams = "uuid," + mobile + "93a20d8c058e447e8e";
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
	public static String getSecUseIdBy8006(String mobile, String userID, String token) throws Exception {
		String params = DataUrls.params_8006;
		String url = DataUrls.url_act;
		String suc = "code\":1";
		String invitationCode = "";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + mobile;
		String bParams = "actTypeId," + actTypeId;
		params = AppReq.setParmas(params, hParams, bParams);
		System.out.println(params);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			invitationCode= data.getString("invitationCode");
			System.out.println(mobile + "," + invitationCode);
		}
		return invitationCode;
	}

	public static boolean regUseByVoiceSms(String mobile) throws Exception {
		boolean result = false;
		String params = DataUrls.params_8001;
		String url = DataUrls.url_act;
		String suc = "code\":1";

		String hParams = "";
		String bParams = "mobile," + mobile;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			String verifyCode = SmsTest.getSmsCode(type , mobile);
			result = regUseBy8002(mobile, verifyCode);
		}
		System.out.println("register result =" + result);
		return result;
	}
	
	public static void test(int s, int e) throws Exception{
		for (int i = s; i < e; i++) {
			String mobile = "1341114";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			regUseByVoiceSms(mobile);
		}
		
		Thread.sleep(30 * 1000);
		
		for (int i = s; i < e; i++) {
			String mobile = "1341114";
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
		
		for (int i = s; i < e; i++) {
			String mobile = "1341114";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			UserBaseInfo.push1000(userID, token);
			
			UserBaseInfo.getRp8341(userID, token);
			
			UserBaseInfo.buyGaoPin(userID, token, 1, "62");
		}
	}
	
	public static void main(String[] args) throws Exception {
//		UserInfo user = UserBaseInfo.getUserInfo("13411130235", pw);
//		String token = user.getToken();
//		String userID = user.getUserId();
//		UserBaseInfo.push1000(userID, token);
		
//		int s = 513;
//		
//		for (int i = 1; i < 13; i++) {
			String mobile = "js006";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			userId = getSecUseIdBy8006(mobile, userID, token);
			
//			int e = s + i;
//			test(s, e);
//			s = e;
//		}
//		System.out.println("*********" + s);
	}
}
