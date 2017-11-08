package smc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import helper.DataUrls;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.HttpRequester;
import utils.HttpRespons;

public class SourceTest {

	private static String jz_url = DataUrls.ft_qlive;
	private static String sslive_url = DataUrls.ft_sslive;

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
//			 System.out.println(json);
		}
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

				if (!NUM.equals("001")) {
					continue;
				}

				// if (IC == 1) {
				// continue;
				// }

				request = new HttpRequester();
				request.setDefaultContentEncoding("UTF-8");
				hr = request.sendGet(sslive_url + match);
				json = hr.getContent();
				if (json == null || json.equals("")) {
					System.out.println("json is empty");
				} else {
//					System.out.println(json);
				}

				obj = JSONObject.fromObject(json);
				JSONObject data = obj.getJSONObject("data");
				String matchStatus = data.getString("matchStatus");
				String source = data.getString("source");
				String score90 = data.getString("score90");

				Date d = new Date();
				String ssss = d.toString() + "\t" + NUM + "\t列表源：" + SO + "\t比分列表时间：" + SS + "\t比分详情时间：" + matchStatus
						+ "\t实况源：" + source + "\t比分" + score90;
				System.out.println(ssss);
				saveToFile(ssss, "log.txt", false);
				matchs = matchs + match;
				if (j < MSarray.size() - 1) {
					matchs = matchs + ",";
				}
			}
		}
		System.out.println();
		return matchs;
	}

	public static void saveToFile(String text, String path, boolean isClose) {
		File file = new File(path);
		BufferedWriter bf = null;
		try {
			FileOutputStream outputStream = new FileOutputStream(file, true);
			OutputStreamWriter outWriter = new OutputStreamWriter(outputStream);
			bf = new BufferedWriter(outWriter);
			bf.append(text);
			bf.newLine();
			bf.flush();

			if (isClose)
				bf.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		for (int i = 0; i < 3 * 60 * 5; i++) {
			getJZMatchs("2017-09-05");
			Thread.sleep(5 * 1000);
		}

	}
}
