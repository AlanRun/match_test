package monitor;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smc.JzIssue;
import utils.HttpRequester;
import utils.HttpRespons;

/**
 * 监测比分列表与比分详情时间一致性
 * @author Alan Huang
 *
 */
public class QliveTest {

	private static String jz_url = DataUrls.ft_qlive;
	private static String sslive_url = "https://smc.jdd.com/api/scoredetail/sslive?lotteryId=90&pcode=h5&pts=0&version=v2.1&mid=";

	static Map<String, String> params = new HashMap<String, String>();

	public static String getJZMatchs(String issue) throws IOException {

		HttpRequester request = new HttpRequester();
		request.setDefaultContentEncoding("utf-8");
		HttpRespons hr = request.sendGet(jz_url + issue);

		String matchs = "";

		String json = hr.getContent();
		if (json == null || json.equals("")) {
			System.out.println("empty content!!!");
			return matchs;
		} else {

		}
		try {
			JSONObject obj = JSONObject.fromObject(json);
			JSONArray MIarray = obj.getJSONArray("MI");

			for (int i = 0; i < MIarray.size(); i++) {
				JSONObject MI = MIarray.getJSONObject(i);
				JSONArray MSarray = MI.getJSONArray("MS");

				for (int j = 0; j < MSarray.size(); j++) {
					JSONObject M = MSarray.getJSONObject(j);
					int match = M.getInt("MId");
					String NUM = M.getString("NUM");
					int IC = M.getInt("IC");
					int SO = M.getInt("SO");
					String SS = M.getString("SS");

//					if (IC != 2) {
//						continue;
//					}

					request = new HttpRequester();
					request.setDefaultContentEncoding("UTF-8");
					hr = request.sendGet(sslive_url + match);
					json = hr.getContent();
					if (json == null || json.equals("")) {
						System.out.println("json is empty");
						;
					} else {

					}

					obj = JSONObject.fromObject(json);
					JSONObject data = obj.getJSONObject("data");
					String matchStatus = data.getString("matchStatus");

					Date d = new Date();
					System.out.println(
							d.toString() + "\t" + NUM + "\t" + SO + "\t比分列表时间：" + SS + "\t比分详情时间：" + matchStatus);

					matchs = matchs + match;
					if (j < MSarray.size() - 1) {
						matchs = matchs + ",";
					}
				}
			}
		} catch (Exception e) {
		}
		System.out.println();
		return matchs;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		for (int j = 0; j < 5; j++) {
			// 获取期次
			String issues = JzIssue.getJZIssues();
			if (!issues.equals("")) {
				String[] issue = issues.split(",");
				for (int i = 0; i < issue.length; i++) {
					// 获取对阵
					System.out.println("=========" + issue[i] + "=========");
					getJZMatchs(issue[i]);
					System.out.println("=========" + issue[i] + "=========\n");
				}
			}
			Thread.sleep(15 * 1000);
		}
	}
}
