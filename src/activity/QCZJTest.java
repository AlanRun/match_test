package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.SmsTest;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 汽车之家活动
 * @author Alan
 *
 */
public class QCZJTest {
	private static String actTypeId = "100002";
	private static String type = "zz";;
	
	public static boolean registerUse7058(String mobile) throws Exception{
		String params = DataUrls.params_7058;
		String url = DataUrls.url_act;
		String suc = "短信发送成功";
		
		String hParams = "";
		String bParams = "mobile," + mobile + ";actTypeId," + actTypeId;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			String verifyCode = SmsTest.getSmsCode(type, mobile);
			System.out.println(verifyCode);
			result = verifyCode7059(mobile, verifyCode);
		}
		
		System.out.println("register result =" + result);
		return result;
	}
	
	public static boolean verifyCode7059(String mobile, String verifyCode) throws Exception{
		String params = DataUrls.params_7059;
		String url = DataUrls.url_act;
		String suc = "您的88礼包已发送至您的彩票账户";
		
		String hParams = "";
		String bParams = "actTypeId," + actTypeId +  ";mobile," + mobile + ";verifyCode," + verifyCode;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean getAtcInfo() throws Exception{
		String params = DataUrls.params_7060;
		String url = DataUrls.url_act;
		String suc = "立即领取";
		
		String hParams = "";
		String bParams = "actTypeId," + actTypeId;
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
				if (TotalMoney.equals("3000")) {
					result = item.getString("SendID");
				}
			}
		}
		
		return result;
	}
	
	public static boolean checkUserRedPackage(String userID, String token) throws Exception{
		String url = DataUrls.url_rp;
		String params = DataUrls.params_402;
		String suc = "code\":0";
		boolean result = false;
		
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
//				String TotalMoney = item.getString("TotalMoney");
				String Name = item.getString("Name");
				if (Name.equals("汽车之家")) {
					result = true;
					System.out.println(Name);
				}
				if (Name.contains("注册")) {
					System.out.println(Name);
				}
			}
		}
		System.out.println("check redpackage " + result);
		return result;
	}

	public static void testA() throws Exception{
		System.out.println("A. 获取活动信息");
		getAtcInfo();
	}
	
	public static void testB() throws Exception{
		System.out.println("B. 老用户领取成功");
		String name = "13811110001";
		String pwd = "aaaaaa";
		registerUse7058(name);
		
		UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
		checkUserRedPackage(user.getUserId(), user.getToken());
	}
	
	public static void testC() throws Exception{
		System.out.println("C. 重复领取");
		String name = "13811110001";
		registerUse7058(name);
	}
	
	public static void testD() throws Exception{
		System.out.println("D. 新用户领取");
		String name = "13801000001";
		String pwd = "aaaaaa";
		registerUse7058(name);
		
		UserBaseInfo.resetPwd(type, name);
		UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
		checkUserRedPackage(user.getUserId(), user.getToken());
	}
	
	public static void testE() throws Exception{
		System.out.println("E. 新用户领取");
		String name = "13801000002";
		String pwd = "aaaaaa";
		registerUse7058(name);
		
		UserBaseInfo.resetPwd(type, name);
		UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
		checkUserRedPackage(user.getUserId(), user.getToken());
	}
	
	public static void testF() throws Exception{
		System.out.println("F. 奖品已领完");
		String name = "13801000003";
		String pwd = "aaaaaa";
		registerUse7058(name);
		
		UserBaseInfo.resetPwd(type, name);
//		UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
//		checkUserRedPackage(user.getUserId(), user.getToken());
	}
	
	public static void testG() throws Exception{
		System.out.println("G. 活动已结束");
		String name = "13801000003";
		registerUse7058(name);
		
		UserBaseInfo.resetPwd(type, name);
	}
	
	public static void main(String[] args) throws Exception {
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 10; i < 20; i++) {
//					try {
						String name = "13411160014";
						String pwd = "aaaaaa";
//						registerUse7058(name);
//						
//						Thread.sleep(5000);
//						
						UserBaseInfo.resetPwd(type, name);
						UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
						checkUserRedPackage(user.getUserId(), user.getToken());
//					} catch (Exception e) {
//					}
//				}
//			}
//		}).start();
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 20; i < 30; i++) {
//					try {
//						String name = "183123400" + i;
//						String pwd = "aaaaaa";
//						registerUse7058(name);
//						
//						UserBaseInfo.resetPwd(type, name);
//						UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
//						checkUserRedPackage(user.getUserId(), user.getToken());
//					} catch (Exception e) {
//					}
//				}
//			}
//		}).start();
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 30; i < 40; i++) {
//					try {
//						String name = "183123400" + i;
//						String pwd = "aaaaaa";
//						registerUse7058(name);
//						
//						UserBaseInfo.resetPwd(type, name);
//						UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
//						checkUserRedPackage(user.getUserId(), user.getToken());
//					} catch (Exception e) {
//					}
//				}
//			}
//		}).start();
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 40; i < 50; i++) {
//					try {
//						String name = "183123400" + i;
//						String pwd = "aaaaaa";
//						registerUse7058(name);
//						
//						UserBaseInfo.resetPwd(type, name);
//						UserInfo user = UserBaseInfo.getUserInfo(name, pwd);
//						checkUserRedPackage(user.getUserId(), user.getToken());
//					} catch (Exception e) {
//					}
//				}
//			}
//		}).start();
		
//		testA();
//		testB();
//		testC();
//		testD();
//		testE();
//		testF();
//		testG();
		
		
		
//		System.out.println("1.");
//		registerUse7058("13811110003");
//		JZZBTest.resetPwd(type, "13811110004");
//		getAtcInfo();
	}
}
