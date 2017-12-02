package smc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class JCSPTest {
	private static String url = "https://smc.jdddata.com/api/odds/geteurodetail?bookid=1000&matchid="; 
	
	public static String getJCSp(String matchId) throws Exception {
		String str = "";
		String URL = url + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		JSONArray oddList = data.getJSONArray("oddsEuroDetailCurrChangeBoList");
		JSONObject odd = (JSONObject) oddList.get(0);
		String winRate = odd.getString("winRate");
		String drawRate = odd.getString("drawRate");
		String lossRate = odd.getString("lossRate");
		str = winRate + "," + drawRate + "," + lossRate;
		return str;
	}
	
	public static String getAveSp(String matchId) throws Exception {
		String str = "";
		String URL = "https://smc.jdddata.com/api/odds/geteurooddslist?matchid=" + matchId;

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(URL);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			return "接口无返回";
		} else {

		}

		JSONObject obj = JSONObject.fromObject(json);
		JSONArray data = obj.getJSONArray("data");
		JSONObject ave = (JSONObject) data.get(0);
		String lastWin = ave.getString("lastWin");
		String lastDraw = ave.getString("lastDraw");
		String lastLoss = ave.getString("lastLoss");
		
		str = "\t" + "百家平均：" + lastWin + "\t" + lastDraw + "\t" + lastLoss;
		return str;
	}
	
	
	public static void main(String[] args) {
		

	}

}
