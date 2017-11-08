package jdd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utils.HttpRequester;
import utils.HttpRespons;

public class FllowTest {
	
	private static String URL = "http://client.jiangduoduo.com/action/MobileHandler.ashx";

	static Map<String, String> params = new HashMap<String, String>();
	static Map<String, String> params1 = new HashMap<String, String>();
	static Map<String, String> params2 = new HashMap<String, String>();

	
	public static void testDaShen() throws IOException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("sign", "BED724D78517B3314DE48446E9C10BA2");
		params.put("platformVersion", "4.4.4");
		params.put("platformCode", "Android");
		params.put("cmdId", "3718969");
		params.put("appVersion", "3.6.0");
		params.put("cmdName", "app_yyb");
		params.put("action", "2071");
		params.put("UserID", "MTA4MDM5NTY=");
		params.put("params", "{'schemeid':40030297,'multiple':10}");
		params.put("uuid", "ffffffff-87b3-1fd3-be31-2c220033c587");
		params.put("isdebug", "1");
		
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr;
		hr = request.sendGet(URL, params);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.err.println("empty content!!!");
		}
	}
	
	public static void testDaShen1() throws IOException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("sign", "E991D57771F1FD1BECB820CBF5E9F021");
		params.put("platformVersion", "5.0.2");
		params.put("platformCode", "Android");
		params.put("cmdId", "559499");
		params.put("appVersion", "3.6.0");
		params.put("cmdName", "app_xmsj");
		params.put("action", "2071");
		params.put("UserID", "MTA4MDQwNDU=");
		params.put("params", "{'schemeid':40030297,'multiple':10}");
		params.put("uuid", "00000000-6918-2f38-24e4-86264d3fd976");
		params.put("isdebug", "1");
		
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr;
		hr = request.sendGet(URL, params);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.err.println("empty content!!!");
		}
	}
	
	public static void testDaShen2() throws IOException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("sign", "061CC1EF6B8D3C87D7C4622C97168CDC");
		params.put("platformVersion", "5.0.2");
		params.put("platformCode", "Android");
		params.put("cmdId", "559499");
		params.put("appVersion", "3.6.0");
		params.put("cmdName", "app_xmsj");
		params.put("action", "2071");
		params.put("UserID", "MTA4MDQwNDY=");
		params.put("params", "{'schemeid':40030297,'multiple':10}");
		params.put("uuid", "00000000-6918-2f38-24e4-86264d3fd976");
		params.put("isdebug", "1");
		
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr;
		hr = request.sendGet(URL, params);
		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.err.println("empty content!!!");
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 500; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 30; j++) {
						try {
							testDaShen();
							testDaShen1();
							testDaShen2();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

}
