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
 * 充值20
 * @author Alan
 *
 */
public class Test108 {

	/**
	 * 108接口调起充值
	 * 
	 * @param userID
	 * @param token
	 * @throws Exception
	 */
	public static void getMechartNo(String userID, String token) throws Exception {
		String url = DataUrls.url_trade;
		String params = DataUrls.params_108;
		String suc = "code\":0";
		String money = "20";

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
	 * 领取充20资格
	 * 
	 * @param username
	 * @param userID
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static boolean getRechageQualify(String username, String userID, String token) throws Exception {
		String url = DataUrls.url_act;
		String params = DataUrls.params_8343;
		String suc = "领取资格成功";
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
	 * 获取用户总额为3000的彩金卡ID
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
			System.err.println(items.size());
//			for (int i = 0; i < items.size(); i++) {
//				JSONObject item = (JSONObject) items.get(i);
//				String TotalMoney = item.getString("TotalMoney");
//				if (TotalMoney.equals("9000")) {
//					result = item.getString("SendID");
//				}
//			}
		}
		
		return result;
	}

	public static void testRechage() throws Exception {
		for (int i = 1; i < 20; i++) {
			String mobile = "1342223";
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
			final String token = user.getToken();
			final String userId = user.getUserId();
			
//			getMechartNo(userId, token);
			
			getUserRedpackage(userId, token);

//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						getMechartNo(userId, token);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//			}).start();
//
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						getMechartNo(userId, token);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//			}).start();
//
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						getMechartNo(userId, token);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//			}).start();
//
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						getMechartNo(userId, token);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//			}).start();
//
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						getMechartNo(userId, token);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//			}).start();
			
			
		}
	}

	public static void main(String[] args) throws Exception {
//		for (int i = 1; i < 20; i++) {
//			String mobile = "1342223";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if (i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
//			String token = user.getToken();
//			String userId = user.getUserId();
//			getRechageQualify(mobile, userId, token);
//		}
		testRechage();
	}

}
