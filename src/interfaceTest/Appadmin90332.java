package interfaceTest;

import java.io.IOException;
import java.text.DecimalFormat;

import com.jdd.fm.core.exception.AesException;
import helper.AppReq;
import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Appadmin90332 {
	
	// 获取双色球当前期次信息
	public static String getCurSSQIssue() throws AesException, IOException{
		String json = AppReq.getResStr(DataUrls.bd_url, DataUrls.params_200_5);

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		JSONObject issue = (JSONObject) data.get(0);
		String EndTime = issue.getString("EndTime");
		String IssueName = issue.getString("IssueName");
		String Balance = issue.getString("Balance");
		
		DecimalFormat df=new DecimalFormat("0.00");
		
		if (!(Balance.equals("") || Balance.equals("0"))) {
			Long b = Long.valueOf(Balance);
			Balance = "" + df.format((float)b/100000000);
		}
		
		String result = "双色球," + IssueName + ",截止=" + EndTime + ",奖池=" + Balance;
		return result;
	}
	
	/**
	 * 获取大乐透当前期次信息
	 * @throws IOException 
	 * @throws AesException 
	 */
	public static String getCurDLTIssue() throws AesException, IOException{
		String json = AppReq.getResStr(DataUrls.bd_url, DataUrls.params_200_39);

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		JSONObject issue = (JSONObject) data.get(0);
		String EndTime = issue.getString("EndTime");
		String IssueName = issue.getString("IssueName");
		String Balance = issue.getString("Balance");
		
		DecimalFormat df=new DecimalFormat("0.00");
		
		if (!(Balance.equals("") || Balance.equals("0"))) {
			Long b = Long.valueOf(Balance);
			Balance = "" + df.format((float)b/100000000);
		}
		
		String result = "大乐透," + IssueName + ",截止=" + EndTime + ",奖池=" + Balance;
		return result;
	}

	public static void check90332() throws AesException, IOException {
		String ssq = getCurSSQIssue();
		String dlt = getCurDLTIssue();
		String tmp = "";
		
		String[] ssqArr = ssq.split(",");
		String[] dltArr = dlt.split(",");
		
		if (ssqArr[2].compareTo(dltArr[2]) < 0) {
			tmp = ssq;
		} else {
			tmp = dlt;
		}
		
		String json = AppReq.getResStr(DataUrls.appadmin_url, DataUrls.params_90332);
		
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		JSONObject numberLottery = data.getJSONObject("numberLottery");
		
		String endTime = numberLottery.getString("endTime");
		String issueName = numberLottery.getString("issueName");
		String lotteryName = numberLottery.getString("lotteryName");
		String money = numberLottery.getString("money");
		
		String result = lotteryName + "," + issueName + ",截止=" + endTime + ",奖池=" + money;
		
		System.out.println("预计=" + tmp);
		System.out.println("实际=" + result);
		
		if (result.equals(tmp)) {
			System.out.println("90332快捷投注数字彩正常");
		} else {
			System.out.println("90332快捷投注数字彩异常");
		}
		
	}
	
	public static void check9009() throws AesException, IOException {
		String json = AppReq.getResStr(DataUrls.appadmin_url, DataUrls.params_9009);
		
		System.out.println(json);
		
	}

	public static void main(String[] args) throws AesException, IOException {
//		getCurSSQIssue();
//		getCurDLTIssue();
		check9009();
	}
}
