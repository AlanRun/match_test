package interfaceTest;

import java.io.IOException;
import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.DataUrls;
import net.sf.json.JSONObject;

public class Info100624Test {

	public static void matchVote(String uuid, String gameId, String type) throws AesException, IOException {
		String url = DataUrls.url_info;
		String params = "{\"header\":{\"appVersion\":\"5.1.3\",\"idfa\":\"834D4F30-3325-40F4-996C-3D1B1908D706\",\"cmdName\":\"app_ios_zz\",\"uuid\":\"9C76BC12A59247DDACB8928CCC301B00\",\"userID\":\"\",\"islogin\":true,\"token\":\"\",\"cmdId\":\"1\",\"platformVersion\":\"10.3.2\",\"imei\":\"\",\"action\":100624,\"userType\":1,\"platformCode\":\"IPHONE\",\"username\":\"\"},\"body\":\"{'gameId':'11874458','type':1,'matchTime':'2017-09-30 10:00:00'}\"}";

		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		header.put("uuid", uuid);
		obj.put("header", header);
		
		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		body.put("gameId", gameId);
		body.put("type", type);

		obj.put("body", body);

		params = obj.toString();

		String json = AppReq.getResStr(url, params);
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
//			System.out.println(json);
		}

		obj = JSONObject.fromObject(json);
		JSONObject data = obj.getJSONObject("data");
		int code = obj.getInt("code");
		if (code == 0) {
			System.out.println(json);
		} else {
			System.out.print("false");
		}
	}

	public static void main(String[] args) throws AesException, IOException {
		String gameId = "11874458";
		String type = "2";
		for (int i = 0; i < 300; i++) {
			String uuid = "mntbrgvfecd";
			System.out.println(i + "\t");
			uuid = uuid + i;
			matchVote(uuid, gameId, type);
		}
		
		type = "3";
		for (int i = 0; i < 300; i++) {
			String uuid = "mjyhbgfv";
			System.out.println(i + "\t");
			uuid = uuid + i;
			matchVote(uuid, gameId, type);
		}
	}
}
