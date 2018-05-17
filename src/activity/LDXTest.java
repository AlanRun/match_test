package activity;

import helper.UserInfo;
import interfaceTest.UserBaseInfo;

/**
 * 老带新活动
 * @author jdd
 *
 */
public class LDXTest {
	
	public static void main(String[] args) throws Exception {
		
		String type = "zz";
		String actTypeId = "48484";
		
		for (int i = 1; i < 1102; i++) {
			String mobile = "1340000";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserBaseInfo.register7054Use(type, mobile, "D8AC6F436813D05E3F87841978B1299C", actTypeId);
		}
		
		for (int i = 1; i < 1102; i++) {
			String mobile = "1340000";
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
		
//		for (int i = 0; i < 1005; i++) {
//			String mobile = "1340000";
//			if (i < 10) {
//				mobile = mobile + "000" + i;
//			} else if ( i < 100) {
//				mobile = mobile + "00" + i;
//			} else if (i < 1000) {
//				mobile = mobile + "0" + i;
//			} else if (i < 10000) {
//				mobile = mobile + i;
//			}
//			UserInfo user = LoginTest.getUserInfo(mobile, "aaaaaa");
//			String token = user.getToken();
//			String userId = user.getUserId();
//			
//			UserBaseInfo.push1000(userId, token, mobile + "04FCEE6BDE0F461FACD85");
//		}
		
//		for (int i = 0; i < 42; i++) {
//			String mobile = "1340000";
//			if (i < 10) {
//				mobile = mobile + "0" + i;
//			} else {
//				mobile = mobile + i;
//			}
//			UserBaseInfo.buySSQ(mobile);
//		}
	}

}
