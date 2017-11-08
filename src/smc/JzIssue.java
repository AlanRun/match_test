package smc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class JzIssue {

	private static String bs_url = DataUrls.bs_issue;

	static Map<String, String> params = new HashMap<String, String>();

	public static String getJZIssues() throws IOException {

		String issues = "";

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(bs_url);

		String json = hr.getContent();

		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return issues;
		} else {

		}
		
		try {
			JSONArray datas = JSONArray.fromObject(json);
			for (int i = 0; i < datas.size(); i++) {
				JSONObject data = (JSONObject) datas.get(i);
				String issue = data.getString("Issue");
				issues = issues + issue;
				if (i < datas.size() -1) {
					issues = issues + ",";
				}
			}
		} catch (Exception e) {
			
		}
		return issues;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getJZIssues());
	}
}
