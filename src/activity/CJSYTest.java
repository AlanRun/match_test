package activity;

import interfaceTest.UserBaseInfo;

/**
 * 彩金收益
 * @author Alan
 *
 */
public class CJSYTest {

	public static void main(String[] args) throws Exception {
		String lottery = "62,67,69,72,74,78" ;
		String[] arr = lottery.split(",");
		
		for (int m = 0; m < 3; m++) {
			for (int j = 0; j < arr.length; j++) {
				String LotteryID = arr[j];
				for (int i = 1; i < 50; i++) {
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
					
					if (LotteryID.equals("90")) {
						UserBaseInfo.buyJZ(mobile, (i%50));
					} else if (LotteryID.equals("91")) {
						UserBaseInfo.buyJL(mobile, (i%50));
					} else {
						UserBaseInfo.buyGaoPin(mobile, (i%50), LotteryID);
					}
				}
			}
		}
	}

}
