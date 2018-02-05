package helper;

import java.io.IOException;
import java.util.Map;

import com.jdd.fm.core.exception.AesException;
import com.jdd.fm.core.utils.TransferAesEncrypt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class AppReq {
	
	public static String getMobileStr(String mobile) throws Exception{
		String reStr = "";
		TransferAesEncrypt.aesEncrypt(mobile, "", "utf-8");
		
		
		
		return reStr;
	}

	public static String getResStr(String url, String params) throws Exception {
		// String url = DataUrls.appadmin_url;
		// String params = DataUrls.params_90332;

		// 加密
		String content = TransferAesEncrypt.aesEncrypt(params, "d3YmI1BUOSE2S2YmalBVZUQ=", "utf-8");
		String re = "request=" + java.net.URLEncoder.encode(content, "utf-8");

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendPost(url, re);
		String json = hr.getContent();

		return json;
	}
	
	public static String getResStrNotAes(String url, String params) throws Exception {

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendPost(url, params);
		String json = hr.getContent();

		return json;
	}
	
	public static String getResStrNotAes(String url,String params, String authorization) throws Exception {

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendPostUseAuthorization(url, params, authorization);
		String json = hr.getContent();

		return json;
	}
	
	public static String getResStrByOptions(String url,String params, String authorization) throws Exception {

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendOptions(url, params, authorization);
		String json = hr.getContent();

		return json;
	}
	
	public static String getResStrByGet(String url) throws Exception{
		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("UTF-8");
		HttpRespons hr = request.sendGet(url);
		String json = hr.getContent();
		return json;
	}

	public static String setParmas(String params, String hParams, String bParams) {

		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");

		if (!hParams.equals("")) {
			String[] hp = hParams.split(";");
			for (int i = 0; i < hp.length; i++) {
				String param = hp[i];
				String[] p = param.split(",");
				header.put(p[0], p[1]);
			}
		}
		obj.put("header", header);

		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);

		if (!bParams.equals("")) {
			String[] bp = bParams.split(";");
			for (int i = 0; i < bp.length; i++) {
				String param = bp[i];
				String[] p = param.split(",");
				body.put(p[0], p[1]);
			}
		}
		obj.put("body", body);
		params = obj.toString();
		return params;
	}
	
	public static String setParmas(String params, String hParams, String bParams, String nParams) {

		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");
		if (!hParams.equals("")) {
			String[] hp = hParams.split(";");
			for (int i = 0; i < hp.length; i++) {
				String param = hp[i];
				String[] p = param.split(",");
				header.put(p[0], p[1]);
			}
		}
		obj.put("header", header);

		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		if (!bParams.equals("")) {
			String[] bp = bParams.split(";");
			for (int i = 0; i < bp.length; i++) {
				String param = bp[i];
				String[] p = param.split(",");
//				if (p[0].equals("Money") || p[0].equals("LotteryID") || p[0].equals("Multiple")) {
//					body.put(p[0], Integer.valueOf(p[1]));
//				} else {
					body.put(p[0], p[1]);
//				}
			}
		}
		
		String NumberS = body.getString("Number");
		JSONObject Number = (JSONObject) JSONArray.fromObject(NumberS).get(0);
		if (!nParams.equals("")) {
			String[] np = nParams.split(";");
			for (int i = 0; i < np.length; i++) {
				String param = np[i];
				String[] p = param.split(",");
//				if (p[0].equals("playid")) {
//					Number.put(p[0], Integer.valueOf(p[1]));
//				} else {
					Number.put(p[0], p[1]);
//				}
			}
		}
		body.put("Number", "[" + Number + "]");
		
		obj.put("body", body);
		params = obj.toString();
		return params;
	}

	@SuppressWarnings("rawtypes")
	public static String setParmas(String params, Map<String, String> hParams, Map<String, String> bParams) {

		JSONObject obj = JSONObject.fromObject(params);
		JSONObject header = obj.getJSONObject("header");

		if (!hParams.equals(null)) {
			for (Map.Entry entry : hParams.entrySet()) {
				header.put(entry.getKey(), entry.getValue());
			}
		}
		obj.put("header", header);

		String bodys = obj.getString("body");
		JSONObject body = JSONObject.fromObject(bodys);
		if (!bParams.equals(null)) {
			for (Map.Entry entry : bParams.entrySet()) {
				body.put(entry.getKey(), entry.getValue());
			}
		}
		obj.put("body", body);
		params = obj.toString();
		return params;
	}

	public static void main(String[] args) throws AesException, IOException {

	}
}
