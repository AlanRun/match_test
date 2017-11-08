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

public class TimeTest {

	private static String jz_url = DataUrls.ft_qlive;
	private static String url = DataUrls.ft_collect;

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

				if (!(NUM.equals("032") || NUM.equals("033"))) {
					continue;
				} else {
					// if (IC == 1) {
					// continue;
					// }

					request = new HttpRequester();
					request.setDefaultContentEncoding("UTF-8");
					hr = request.sendGet(url + match);
					json = hr.getContent();
					if (json == null || json.equals("")) {
						System.out.println("json is empty");
					} else {
//						System.out.println(json);
					}

					obj = JSONObject.fromObject(json);
					MIarray = obj.getJSONArray("MI");
					MI = MIarray.getJSONObject(i);
					MSarray = MI.getJSONArray("MS");
					M = MSarray.getJSONObject(0);
					int SO1 = M.getInt("SO");
					String SS1 = M.getString("SS");

					Date d = new Date();
					String ssss = d.toString() + "\t" + NUM + "\t列表源：" + SO + "\t比分列表时间：" + SS + "\t方案详情时间：" + SS1
							+ "\t方案源：" + SO1;
					System.out.println(ssss);
					saveToFile(ssss, "log.txt", false);
					matchs = matchs + match;
					if (j < MSarray.size() - 1) {
						matchs = matchs + ",";
					}
					break;
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

		for (int i = 0; i < 9 * 60 * 5; i++) {
			getJZMatchs("2017-09-05");
			Thread.sleep(1 * 1000);
		}

	}
}
