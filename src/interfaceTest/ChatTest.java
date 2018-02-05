package interfaceTest;

import java.io.IOException;

import com.jdd.fm.core.exception.AesException;

import helper.AppReq;
import helper.UserInfo;
import net.sf.json.JSONObject;

public class ChatTest {

	static String url = "http://chat.jdd.com/webSocketServer.do";
	static String params = "{\"header\":{\"appVersion\":\"3.8.9\",\"cmdId\":0,\"platformVersion\":\"6.0\",\"cmdName\":\"app_zz\",\"userType\":1,\"uuid\":\"00000000-7fc6-bc83-0ab8-a6690033c587\",\"userID\":\"ODk5\",\"platformCode\":\"h5mobile\",\"token\":\"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJqc2NwIiwiaXNzIjoiamRkLmNvbSJ9.eyJ1c2VyVHlwZSI6MSwidXNlcmlkIjo4OTksInV1aWQiOiIzQzA3NTU1NU45TTAifQ.7a37c4913e1940192f3249cf3b5f10f5.NmMyYmI1MDEtNDNkZS00NmJjLWExYmItOTljYzgwYzJlYmIz\"},\"body\":\"{'matchId':'9291685'}\"}";
	
	public static void sendChat(UserInfo user) throws Exception{
		
		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		header.put("userID", user.getUserId());
		header.put("token", user.getToken());
		
		System.out.println(obj);
		
		String json = AppReq.getResStr(url, params);
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
		} else {
			System.out.println(json);
		}
		
		obj = JSONObject.fromObject(json);
	}
	
	
	public static void main(String[] args) throws Exception {
		UserInfo user = LoginTest.getUserInfo("13811110001", "aaaaaa");
		sendChat(user);
	}

}
