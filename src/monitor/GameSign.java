package monitor;

import activity.XNJFTest;
import helper.AppReq;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameSign {

	public static void sign(String token, String userID) throws Exception {
		String game_token = getGameToken(token, userID);
		String Authorization = getAuthorization(game_token );
		signAndGetGift(Authorization);
	}
	
	public static void playGame(String token, String userID) throws Exception {
		String game_token = getGameToken(token, userID);
		String Authorization = getAuthorization(game_token );
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
				playBilliards(Authorization);
				getBilliardsTask(Authorization);
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
		String url_login = "https://uic-api.beeplay123.com/uic/api/lottery/login";
		String params_login = "{\"token\":\"" + token + "\",\"userID\":\"" + userID
				+ "\",\"userType\":1,\"appVersion\":\"5.1.8\",\"platformCode\":\"IPHONE\"}";

		String re_login = AppReq.getResStrNotAes(url_login, params_login);
		System.out.println("login=" + re_login);
		JSONObject obj_login = JSONObject.fromObject(re_login);
		String game_token = obj_login.getString("data");
		return game_token;
	}
	
	/**
	 * use token to get Authorization
	 * @param game_token
	 * @return
	 * @throws Exception
	 */
	public static String getAuthorization(String game_token) throws Exception{
		String url_access = "https://uic-api.beeplay123.com/uic/api/user/login/accessToken";
		String params_access = "{\"token\":\"" + game_token + "\",\"type\":1}";
		String re_access = AppReq.getResStrNotAes(url_access, params_access);
		System.out.println("accessToken=" + re_access);
		JSONObject obj_access = JSONObject.fromObject(re_access);
		JSONObject data_access = obj_access.getJSONObject("data");
		String Authorization = data_access.getString("accessToken");
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
		String url_trans = "https://uic-api.beeplay123.com/uic/api/user/login/transInfo";
		String re_trans = AppReq.getResStrNotAes(url_trans, "", Authorization);
		System.out.println("transInfo=" + re_trans);
		int amount = 0;
		if (re_trans.contains("code\":200")) {
			JSONObject obj = JSONObject.fromObject(re_trans);
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
		String url_showlist = "https://platform-api.beeplay123.com/wap/api/usertask/showlist";
		String re_showlist = AppReq.getResStrNotAes(url_showlist, "", Authorization);
		System.out.println("showlist" + re_showlist);

		if (re_showlist.contains("code\":200")) {
			JSONObject obj = JSONObject.fromObject(re_showlist);
			JSONArray data = obj.getJSONArray("data");

			for (int i = 0; i < data.size(); i++) {
				JSONObject task = (JSONObject) data.get(i);
				int finishNum = task.getInt("finishNum");
				int taskStatus = task.getInt("taskStatus");
				if (finishNum == 1 && taskStatus != 2) {
					String url_finish = "https://platform-api.beeplay123.com/wap/api/usertask/finish";
					String param_finish = "{\"taskId\":76,\"taskLogId\":8074673}";
					String re_finish = AppReq.getResStrNotAes(url_finish, param_finish, Authorization);
					System.out.println("task=" + re_finish);
				}
			}
		}
	}

	/**
	 * play
	 * @param Authorization
	 * @throws Exception
	 */
	public static void playBilliards(String Authorization) throws Exception {
		String url_odds = "https://game-api.beeplay123.com/billiards/api/get/odds";
		String param_odds = "{\"nav\":1,\"rate\":100}";
		String re_odds = AppReq.getResStrNotAes(url_odds, param_odds, Authorization);
		System.out.println("play=" + re_odds);
	}

	/**
	 * get count amount
	 * @param Authorization
	 * @return
	 * @throws Exception
	 */
	public static int getBilliardsAmount(String Authorization) throws Exception {
		String url_profitNext = "https://platform-api.beeplay123.com/wap/api/history/profitNext";
		String re_profitNext = AppReq.getResStrNotAes(url_profitNext, "", Authorization);
		int amount = 0;
		System.out.println("profitNext=" + re_profitNext);
		if (re_profitNext.contains("code\":200")) {
			JSONObject obj = JSONObject.fromObject(re_profitNext);
			JSONObject data = obj.getJSONObject("data");
			amount = data.getInt("amount");
		}
		return amount;
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
			UserBaseInfo.getRpNum107(mobile, userID, token);
			UserBaseInfo.getUserRedpackage(userID, token);
			UserBaseInfo.sign20004(userID, token, mobile);
			UserBaseInfo.drawLott7103(userID, token, mobile);
			UserBaseInfo.drawLott7101(userID, token, mobile);
			
			sign(token, userID);
			XNJFTest.getCard7091(mobile, userID, token);
		}
		
		for (int i = 0; i < mList.length; i++) {
			String mobile = mList[i];

			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();

			playGame(token, userID);
		}

	}
}
