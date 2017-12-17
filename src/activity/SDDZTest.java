package activity;

import java.io.IOException;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONObject;

/**
 * 圣诞大战活动
 * @author jdd
 *
 */
public class SDDZTest {
	
	private static String actTypeId = "100006";
	private static String type = "zz";;
	
	
	/**
	 * 领取圣诞资格
	 * @param username
	 * @param id
	 * @param mobile
	 * @param newpwd
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean getActQualify(String name, String userID, String token) throws AesException, IOException{
		String params = DataUrls.params_7065;
		String url = DataUrls.url_act;
		String suc = "资格领取成功";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "actTypeId," + actTypeId;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			String cardId = data.getString("cardId");
			System.out.println(name + " 领取资格：" + cardId);
			result = true;
		}
		return result;
	}
	
	
	/**
	 * 7045分享
	 * @param mobile
	 * @param Multiple
	 * @return
	 * @throws AesException
	 * @throws IOException
	 */
	public static boolean get7045(String name, String userID, String token) throws AesException, IOException{
		String url = DataUrls.url_act;
		String params = DataUrls.params_7045;
		String suc = "圣诞大战NBA";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "actTypeId," + actTypeId;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			result = true;
		}
		return result;
		
	}
	
	public static void main(String[] args) throws Exception {
		
//		String actTypeId = "48484";
//		String mobile = "13422260001";
//		int Multiple = 2;
//		UserBaseInfo.buyJL(mobile, Multiple);
		
//		for (int i = 1; i < 1005; i++) {
//			String mobile = "1342226";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserBaseInfo.register7054Use(type, mobile, "D8AC6F436813D05E3F87841978B1299C", actTypeId);
//		}
//		
//		for (int i = 1; i < 1005; i++) {
//			String mobile = "1342226";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserBaseInfo.resetPwd(type, mobile);
//		}
//		
//		for (int i = 1; i < 1005; i++) {
//			String mobile = "1342226";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
//			String token = user.getToken();
//			String userId = user.getUserId();
//			
//			UserBaseInfo.push1000(userId, token, mobile + "04FCEE6BDE0F461FACD85");
//		}
//		
//		for (int i = 1; i < 1005; i++) {
//			String mobile = "1342226";
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
//			getActQualify(mobile, userId, token);
//			
//			get7045(mobile, userId, token);
//		}
		
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i < 1005; i++) {
				String mobile = "1342226";
				if (i < 10) {
					mobile = mobile + "000" + i;
				} else if ( i < 100) {
					mobile = mobile + "00" + i;
				} else if (i < 1000) {
					mobile = mobile + "0" + i;
				} else if (i < 10000) {
					mobile = mobile + i;
				}
				
				 if (i > 100) {
					UserBaseInfo.buyJL(mobile, 52);
				}else if (i > 1) {
					UserBaseInfo.buyJL(mobile, 25);
				} else {
					UserBaseInfo.buyJL(mobile, 10);
				}
			}
		}
		
	}

}
