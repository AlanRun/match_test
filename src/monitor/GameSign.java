package monitor;

import activity.SJBJGQ;
import helper.AppReq;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameSign {

	private static final String suc = "code\":200";
	public static void sign(String token, String userID) throws Exception {
		String game_token = getGameToken(token, userID);
		String Authorization = getAuthorization(game_token );
		signAndGetGift(Authorization);
	}
	
	public static void playGame(String token, String userID) throws Exception {
		String game_token = getGameToken(token, userID);
		String Authorization = getAuthorization(game_token );
		useBet(Authorization);
		getRich(Authorization);
		playGameAndCheckAward(Authorization);
	}
	
	public static void playGameAndCheckAward(String Authorization) throws Exception{
		int count = getAmountCount(Authorization);
		System.err.println("count=" + count);
		int amount = getBilliardsAmount(Authorization);
		if (count > 0) {
			count = 1;
		}
		for (int i = 0; i < count; i++) {
			if (amount < 1) {
				boolean re = playBilliards(Authorization);
				getBilliardsTask(Authorization);
				if (!re) {
					break;
				}
			} else {
				break;
			}
			amount = getBilliardsAmount(Authorization);
//			count = getAmountCount(Authorization);
		}
	}
	
	/**
	 * login
	 * @param token
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public static String getGameToken(String token, String userID) throws Exception{
		String url = "https://uic-api.beeplay123.com/uic/api/lottery/login";
		String params = "{\"token\":\"" + token + "\",\"userID\":\"" + userID
				+ "\",\"userType\":1,\"appVersion\":\"5.1.8\",\"platformCode\":\"IPHONE\"}";

		String re = AppReq.getResStrNotAes(url, params);
		System.out.println("login=" + re);
		JSONObject obj = JSONObject.fromObject(re);
		String game_token = obj.getString("data");
		return game_token;
	}
	
	/**
	 * use token to get Authorization
	 * @param game_token
	 * @return
	 * @throws Exception
	 */
	public static String getAuthorization(String game_token) throws Exception{
		String url = "https://uic-api.beeplay123.com/uic/api/user/login/accessToken";
		String params = "{\"token\":\"" + game_token + "\",\"type\":1}";
		String re = AppReq.getResStrNotAes(url, params);
		System.out.println("accessToken=" + re);
		JSONObject obj = JSONObject.fromObject(re);
		JSONObject data = obj.getJSONObject("data");
		String Authorization = data.getString("accessToken");
		return Authorization;
	}
	
	/**
	 * sign And Get Gift
	 * @param Authorization
	 * @throws Exception
	 */
	public static void signAndGetGift(String Authorization) throws Exception{
		// get reward list
		String url_check = "https://platform-api.beeplay123.com/wap/api/userchecklottery/checkawardlist";
		String re_check = AppReq.getResStrNotAes(url_check, "", Authorization);
		System.out.println("before sign === " + re_check);

		//sign
		String url_sign = "https://platform-api.beeplay123.com/wap/api/userchecklottery/lottery";
		String re_sign = AppReq.getResStrNotAes(url_sign, "", Authorization);
		System.out.println("userchecklottery=" + re_sign);

		// get reward list
		re_check = AppReq.getResStrNotAes(url_check, "", Authorization);
		System.out.println("after sign === " + re_check);

		String url_point2 = "https://data-api.beeplay123.com/data/api/behaviorRecord/point";
		String params_point2 = "{\"behaviorEventId\":131306}";
		String re_point2 = AppReq.getResStrNotAes(url_point2, params_point2, Authorization);
		System.out.println(re_point2);

		JSONObject obj_check = JSONObject.fromObject(re_check);
		JSONObject data_check = obj_check.getJSONObject("data");
		JSONArray checkAwardList = data_check.getJSONArray("checkAwardList");
		String isReceive = "";
		for (int i = 0; i < checkAwardList.size(); i++) {
			JSONObject award = (JSONObject) checkAwardList.get(i);
			if (i != 0) {
				isReceive = isReceive + ";";
			}
			isReceive = isReceive + award.getString("activityDay") + "," + award.getString("isReceive");
		}
		System.out.println(isReceive);

		String url_award = "https://platform-api.beeplay123.com/wap/api/userchecklottery/receivecheckaward";

		if (!isReceive.equals("")) {
			String[] P = isReceive.split(";");
			for (int i = 0; i < P.length; i++) {
				String params_award = "{\"checkDay\":";
				String param = P[i];
				String[] p = param.split(",");
				if (p[1].equals("4")) {
					params_award = params_award + p[0] + "}";
				} else {
					continue;
				}
				System.out.println(params_award);
				String re_award = AppReq.getResStrNotAes(url_award, params_award, Authorization);
				System.out.println(re_award);
			}
		}
	}

	/**
	 * get play count
	 * @param Authorization
	 * @return
	 * @throws Exception
	 */
	public static int getAmountCount(String Authorization) throws Exception {
		String url = "https://uic-api.beeplay123.com/uic/api/user/login/transInfo";
		String re = AppReq.getResStrNotAes(url, "", Authorization);
		System.out.println("transInfo=" + re);
		int amount = 0;
		if (re.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(re);
			JSONObject data = obj.getJSONObject("data");
			amount = data.getInt("amount");
		}
		if (amount == 0) {
			return 0;
		} else {
			return (amount / 100);
		}
	}

	/**
	 * get task
	 * @param Authorization
	 * @throws Exception
	 */
	public static void getBilliardsTask(String Authorization) throws Exception {
		String url = "https://platform-api.beeplay123.com/wap/api/usertask/showlist";
		String re = AppReq.getResStrNotAes(url, "", Authorization);
		System.out.println("showlist=" + re);

		if (re.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(re);
			JSONArray data = obj.getJSONArray("data");

			for (int i = 0; i < data.size(); i++) {
				JSONObject task = (JSONObject) data.get(i);
				int taskId = task.getInt("taskId");
				int taskStatus = task.getInt("taskStatus");
				String taskLogId = task.getString("taskLogId");
				if (taskStatus == 0) {
					String url_finish = "https://platform-api.beeplay123.com/wap/api/usertask/finish";
					String param_finish = "{\"taskId\":"+ taskId +",\"taskLogId\":"+ taskLogId +"}";
					String re_finish = AppReq.getResStrNotAes(url_finish, param_finish, Authorization);
					System.out.println("task=" + re_finish);
				}
			}
		}
	}

	/**
	 * play
	 * @param Authorization
	 * @return 
	 * @throws Exception
	 */
	public static boolean playBilliards(String Authorization) throws Exception {
		String url = "https://game-api.beeplay123.com/billiards/api/get/odds";
		String param = "{\"nav\":1,\"rate\":100}";
		String err = "请合理出杆";
		String re = AppReq.getResStrNotAes(url, param, Authorization);
		System.out.println("play=" + re);
		if (re.contains(err)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * get count amount
	 * @param Authorization
	 * @return
	 * @throws Exception
	 */
	public static int getBilliardsAmount(String Authorization) throws Exception {
		String url = "https://platform-api.beeplay123.com/wap/api/history/profitNext";
		String re = AppReq.getResStrNotAes(url, "", Authorization);
		int amount = 0;
		System.out.println("profitNext=" + re);
		if (re.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(re);
			JSONObject data = obj.getJSONObject("data");
			amount = data.getInt("amount");
		}
		return amount;
	}
	
	public static void useBet(String Authorization) throws Exception{
		String url = "https://ops-api.beeplay123.com/ops/api/richwheel/commonStatus";
		String param = "{\"value\":4}";
		String re = AppReq.getResStrNotAes(url, param, Authorization);
		System.out.println("commonStatus=" + re);
		if (re.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(re);
			JSONObject data = obj.getJSONObject("data");
			int bettingTimes = data.getInt("bettingTimes");
			
			for (int i = 0; i < bettingTimes; i++) {
				getCommonBet(Authorization);
			}
		}
	}
	
	public static void getCommonBet(String Authorization) throws Exception{
		String url = "https://ops-api.beeplay123.com/ops/api/richwheel/commonBetting";
		String param = "{\"value\":4}";
		String reString = AppReq.getResStrNotAes(url, param, Authorization);
		System.out.println("commonBetting=" + reString);
	}
	public static void getRich(String Authorization) throws Exception{
		String url = "https://trans-api.beeplay123.com/trans/api/fragment/rich";
		String param = "{\"page\":1,\"pageSize\":100}";
		String reString = AppReq.getResStrNotAes(url, param, Authorization);
		System.out.println("rich=" + reString);
	}

	public static void main(String[] args) throws Exception {
		String mobiles = "13911112222,18911112222,18917171717,18913131313,18919191919,18921212121,18924242424,18926262626,18925252525,18929292929,18928282828,18701639930,18601950610";
		String pw = "aaaaaa";

		String[] mList = mobiles.split(",");
		for (int i = 0; i < mList.length; i++) {
			String mobile = mList[i];

			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			

			UserBaseInfo.push1000(userID, token);
			SJBJGQ.useFlagCount(userID, token, "111941");
			SJBJGQ.getFlagNum(mobile, userID, token, "111941");
			UserBaseInfo.getRpNum107(mobile, userID, token);
			UserBaseInfo.getUserRedpackage(userID, token);
			UserBaseInfo.sign20004(userID, token, mobile);
			UserBaseInfo.drawLott7103(userID, token, mobile);
			UserBaseInfo.drawLott7101(userID, token, mobile);
			
			try {
				sign(token, userID);
			} catch (Exception e) {
				System.out.println("Sign game failed.");
			}
		}
		
		for (int i = 0; i < mList.length; i++) {
			String mobile = mList[i];

			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();

			try {
				playGame(token, userID);
			} catch (Exception e) {
				System.out.println("Play game failed.");
			}
		}

	}
}
