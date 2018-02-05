package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;

/**
 * 世界杯主力俄罗斯
 * @author Alan
 *
 */
public class SJBZL {
	
	private static String type = "zz";
	private static String actTypeId = "100016";
	private static String userId = "9C0F803EBC0D6BC443F1103A9B3B1CCE";
	private static String pw = "aaaaaa";
	
	public static boolean  regUseBy8002(String mobile, String verifyCode) throws Exception {
		String params = DataUrls.params_8002;
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
			System.out.println(verifyCode);
			result = regUseBy8002(mobile, verifyCode);
		}
		System.out.println("register result =" + result);
		return result;
	}
	
	public static void test(int s, int e) throws Exception{
		for (int i = s; i < e; i++) {
			String mobile = "1341113";
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
		
		for (int i = s; i < e; i++) {
			String mobile = "1341113";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserBaseInfo.resetPwd(type, "13411111465");
		}
		
		for (int i = s; i < e; i++) {
			String mobile = "1341113";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw );
			String token = user.getToken();
			String userID = user.getUserId();
			UserBaseInfo.push1000(userID, token);
			
			UserBaseInfo.getMechartNo(userID, token, "2000");
			
			UserBaseInfo.buyGaoPin(userID, token, 1, "62");
		}
	}
	
	public static void main(String[] args) throws Exception {
		test(0, 30);
	}

}
