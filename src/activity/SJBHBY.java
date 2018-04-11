package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONObject;

public class SJBHBY {

	private final static String childReferenceId = "2";
	private final static String referenceId = "13649005";
	private final static String parentReferenceId = "16";
	private final static String actTypeId = "100017";
//	private final static String actTypeId = "111933";
	private final static String pw = "aaaaaa";

	/**
	 * 
	 * @param childReferenceId
	 *            世界杯阶段
	 * @param referenceId
	 *            比赛ID
	 * @return
	 * @throws Exception
	 */
	public static String getGenerateId(String childReferenceId, String referenceId, String parentReferenceId) throws Exception {
		String params = DataUrls.params_500;
		String url = DataUrls.url_act_rain;
		String suc = "生成成功";
		String redPacketId = "";

		String hParams = "";
		String bParams = "referenceId," + referenceId + ";childReferenceId," + childReferenceId + ";parentReferenceId,"  + parentReferenceId;
		params = AppReq.setParmas(params, hParams, bParams);
		System.err.println(params);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			redPacketId = data.getString("id");
		}
		return redPacketId;
	}

	public static void getDoubleRP(String encryptId, String generateIds) throws Exception {
		String params = DataUrls.params_503;
		String url = DataUrls.url_act_rain;
		String suc = "翻倍成功";

		String hParams = "";
		String bParams = "actTypeId," + actTypeId + ";encryptId," + encryptId + ";generateIds," + generateIds;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		// System.out.println(reString);
		if (reString.contains(suc)) {
		}
	}

	/**
	 * 
	 * @param userID
	 *            用户ID
	 * @param token
	 *            用户token
	 * @param referenceId
	 *            比赛ID
	 * @param generateId
	 *            红包雨ID
	 * @throws Exception
	 */
	public static void getRedPacketByChat(String userID, String token, String referenceId, String generateId)
			throws Exception {
		String params = DataUrls.params_502;
		String url = DataUrls.url_act_rain;
		String suc = "code\":1";

		String uuid = token.substring(token.length() - 32, token.length());

		String hParams = "userID," + userID + ";token," + token + ";uuid," + uuid;
		String bParams = "referenceId," + referenceId + ";generateId," + generateId;
		params = AppReq.setParmas(params, hParams, bParams);
		System.err.println(params);
		String reString = AppReq.getResStr(url, params);
		System.out.println(userID + "===" + reString);
		String gainMoney = "";
		String shareId = "";
		String userId = "";
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			gainMoney = data.getString("gainMoney");
			if (!gainMoney.equals("0")) {
				shareId = data.getString("shareId");
				userId = data.getString("userId");
				
				getDoubleRP(userId, shareId);
			}
		}
		System.err.println("gainMoney=" + gainMoney);
	}

	public static void test(int s, int e, String generateId) throws Exception {
		for (int i = s; i < e; i++) {
			String mobile = "1341111";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if (i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}

			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			getRedPacketByChat(userID, token, referenceId, generateId);
		}
	}

	public static void test(int s, String generateId) throws Exception {
		for (int i = s; i < s + 30; i++) {
			String mobile = "1341111";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if (i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}

			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			for (int j = 0; j < 5; j++) {
				getRedPacketByChat(userID, token, referenceId, generateId);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		final String generateId = getGenerateId(childReferenceId, referenceId, parentReferenceId);
		Thread.sleep(2000);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test(1, generateId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test(31, generateId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test(61, generateId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
