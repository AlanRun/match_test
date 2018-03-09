package activity;

import helper.UserInfo;
import interfaceTest.UserBaseInfo;

/**
 * world cup rank act
 * @author Alan
 *
 */
public class SJBPHB {
	private static final String pw = "aaaaaa";
	
	public static void test() throws Exception{
		for (int i = 1; i < 105; i++) {
			String mobile = "1341111";
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
			
			UserBaseInfo.buyJZ(userID, token, i);
		}
	}
	
	public static void check() throws Exception{
		for (int i = 975; i < 100; i++) {
			String mobile = "1341111";
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
			System.err.print(mobile + "\t");
			UserBaseInfo.getUserRedpackage(userID, token, "世界杯中奖排行榜");
		}
	}
	
	public static void main(String[] args) throws Exception {
		test();
//		check();
	}

}
